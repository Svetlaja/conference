package nekrasova.svetlana.conference.repository;

import nekrasova.svetlana.conference.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String userName);

}
