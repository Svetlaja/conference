package nekrasova.svetlana.conference;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ConferenceApplication {

    public static void main(String[] args) {

        SpringApplication.run(ConferenceApplication.class, args);


    }

}
