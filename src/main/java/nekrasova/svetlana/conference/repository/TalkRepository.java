package nekrasova.svetlana.conference.repository;

import nekrasova.svetlana.conference.entity.Talk;
import nekrasova.svetlana.conference.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TalkRepository extends JpaRepository<Talk, Long> {
    List<Talk> findBySpeakers(User user);




}
