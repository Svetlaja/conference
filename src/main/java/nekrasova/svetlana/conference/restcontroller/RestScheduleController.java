package nekrasova.svetlana.conference.restcontroller;

import nekrasova.svetlana.conference.entity.Schedule;
import nekrasova.svetlana.conference.repository.ScheduleRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestScheduleController {
    private final ScheduleRepository scheduleRepository;

    RestScheduleController(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @GetMapping("/schedule")
    List<Schedule> allSchedule() {
        return scheduleRepository.findAll();
    }
}
