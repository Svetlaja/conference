package nekrasova.svetlana.conference.restcontroller;

import nekrasova.svetlana.conference.entityDto.ScheduleByRoomDto;
import nekrasova.svetlana.conference.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/getFullSchedule")
public class RestScheduleController {
    @Autowired
    ScheduleService scheduleService;

    @GetMapping("")
    List<ScheduleByRoomDto> getFullSchedule() {
        return scheduleService.getFullSchedule();
    }
}
