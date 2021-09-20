package nekrasova.svetlana.conference.service;

import nekrasova.svetlana.conference.entity.User;
import nekrasova.svetlana.conference.entity.enums.Role;
import nekrasova.svetlana.conference.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @PersistenceContext
    private EntityManager em;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(userName);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }

    public User findUserById(Long userId) {
        Optional<User> userFromDB = userRepository.findById(userId);
        return userFromDB.orElse(new User());
    }

    public List<User> allUsers() {
        return userRepository.findAll();
    }

    //    public boolean saveUser(User user) {
//        User userFromDB = userRepository.findByUserName(user.getUsername());
//
//        if (userFromDB != null) {
//            return false;
//        }
//        user.setRole(Role.LISTENER);
//        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//        userRepository.save(user);
//        return true;
//    }
    public User saveUser(User user) {
        User userFromDB = userRepository.findByUserName(user.getUsername());

        if (userFromDB != null) {
            return null;
        }
        //user.setRole(Role.LISTENER);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);

    }

    public User registerUser(User user) {
        User userFromDB = userRepository.findByUserName(user.getUsername());

        if (userFromDB != null) {
            return null;
        }
        user.setRole(Role.LISTENER);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);

    }

    public boolean deleteUser(Long userId) {
        if (userRepository.findById(userId).isPresent()) {
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }

    public boolean setSpeaker(Long userId) {
        if (userRepository.findById(userId).isPresent()) {
            User user = userRepository.findById(userId).get();
            user.setRole(Role.SPEAKER);
            userRepository.save(user);
            return true;
        }
        return false;
    }
}
