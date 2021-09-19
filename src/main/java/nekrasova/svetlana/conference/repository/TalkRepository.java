package nekrasova.svetlana.conference.repository;

import nekrasova.svetlana.conference.entity.Talk;
import nekrasova.svetlana.conference.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TalkRepository extends CrudRepository<Talk, Long> {
    List<Talk> findBySpeakers(User user);

}
