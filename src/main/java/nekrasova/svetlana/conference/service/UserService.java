package nekrasova.svetlana.conference.service;

import nekrasova.svetlana.conference.entity.Talk;
import nekrasova.svetlana.conference.entity.User;
import nekrasova.svetlana.conference.entity.enums.Role;
import nekrasova.svetlana.conference.entityDto.SpeakerDto;
import nekrasova.svetlana.conference.entityDto.UserDto;
import nekrasova.svetlana.conference.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }

    public User getAuthorizedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        return (User) loadUserByUsername(currentUserName);
    }

    public User findUserById(Long userId) {
        Optional<User> userFromDB = userRepository.findById(userId);
        return userFromDB.orElse(null);
    }

    public List<UserDto> allUsers() {
        List<UserDto> userDtos = new ArrayList<>();
        for (User user : userRepository.findAll()) {
            userDtos.add(UserDto.fromUser(user));
        }
        return userDtos;
    }

    public List<SpeakerDto> getSpeakers() {
        List<SpeakerDto> speakerDtos = new ArrayList<>();
        for (User user : userRepository.findByRole(Role.SPEAKER)) {
            speakerDtos.add(SpeakerDto.getFromUser(user));
        }
        return speakerDtos;
    }

    public boolean ifExistsUserByName(String userName) {
        User userFromDB = userRepository.findByUsername(userName);
        if (userFromDB != null) {
            return true;
        }
        return false;
    }

    public User createUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUserName());
        user.setRole(Role.valueOf(userDto.getRoleName()));
        user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        return userRepository.save(user);
    }

    public User updateUser(User user, UserDto userDto) {
        user.setUsername(userDto.getUserName());
        user.setRole(Role.valueOf(userDto.getRoleName()));
        user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        return userRepository.save(user);
    }

    public User registerUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUserName());
        user.setRole(Role.LISTENER);
        user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));

        return userRepository.save(user);

    }

    public void deleteUser(User user) {
        for (Talk talk : user.getSpeakersTalks()) {
            user.removeTalk(talk);
        }
        userRepository.delete(user);

    }

    public void setSpeaker(User user) {
        user.setRole(Role.SPEAKER);
        userRepository.save(user);
    }
}
