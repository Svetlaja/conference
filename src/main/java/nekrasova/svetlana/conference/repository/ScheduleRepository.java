package nekrasova.svetlana.conference.repository;

import nekrasova.svetlana.conference.entity.Schedule;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ScheduleRepository extends CrudRepository<Schedule, Long> {
    List<Schedule> findAllOrderByRoomAsc();
}
