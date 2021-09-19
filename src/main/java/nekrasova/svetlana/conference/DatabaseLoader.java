package nekrasova.svetlana.conference;

import nekrasova.svetlana.conference.entity.User;
import nekrasova.svetlana.conference.entity.enums.Role;
import nekrasova.svetlana.conference.repository.TalkRepository;
import nekrasova.svetlana.conference.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements CommandLineRunner {
    private final UserRepository userRepository;
    private final TalkRepository talkRepository;

    @Autowired
    public DatabaseLoader(UserRepository userRepository, TalkRepository talkRepository) {
        this.userRepository = userRepository;
        this.talkRepository = talkRepository;
    }

    @Override
    public void run(String... strings) {
        this.userRepository.save(new User("admin", "1", Role.ADMIN));
        this.userRepository.save(new User("speak 1", "s1", Role.SPEAKER));
        this.userRepository.save(new User("speak 2", "s2", Role.SPEAKER));
    }

}