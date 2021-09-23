package nekrasova.svetlana.conference.repository;

import nekrasova.svetlana.conference.entity.User;
import nekrasova.svetlana.conference.entity.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    List<User> findByRole(Role role);

}
