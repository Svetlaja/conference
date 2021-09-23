package nekrasova.svetlana.conference.repository;

import nekrasova.svetlana.conference.entity.Room;
import nekrasova.svetlana.conference.entity.Schedule;
import nekrasova.svetlana.conference.entity.Talk;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    Schedule findByTalk(Talk talk);

    List<Schedule> findByRoom(Room room, Sort sort);

}
