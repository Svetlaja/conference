package nekrasova.svetlana.conference;

import nekrasova.svetlana.conference.entity.Room;
import nekrasova.svetlana.conference.entity.Schedule;
import nekrasova.svetlana.conference.entity.Talk;
import nekrasova.svetlana.conference.entity.User;
import nekrasova.svetlana.conference.entity.enums.Role;
import nekrasova.svetlana.conference.repository.RoomRepository;
import nekrasova.svetlana.conference.repository.ScheduleRepository;
import nekrasova.svetlana.conference.repository.TalkRepository;
import nekrasova.svetlana.conference.repository.UserRepository;
import nekrasova.svetlana.conference.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class DatabaseLoader implements CommandLineRunner {
    private final UserRepository userRepository;
    private final UserService userService;
    private final TalkRepository talkRepository;
    private final RoomRepository roomRepository;
    private final ScheduleRepository scheduleRepository;

    @Autowired
    public DatabaseLoader(UserRepository userRepository, UserService userService, TalkRepository talkRepository,
                          RoomRepository roomRepository,ScheduleRepository scheduleRepository) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.talkRepository = talkRepository;
        this.roomRepository = roomRepository;
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public void run(String... strings) {
        this.userService.saveUser(new User("admin", "admin", Role.ADMIN));
        this.roomRepository.save(new Room("Room 100"));
        Room room100 = this.roomRepository.findById(1L).get();

        User user1 = new User("speak 1", "sp1", Role.SPEAKER);

        Talk talk1 = new Talk("Talk 1");
        Schedule schedule = new Schedule();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime dtstart = LocalDateTime.parse("19-09-2021 13:00", formatter);
        LocalDateTime dtend = LocalDateTime.parse("19-09-2021 14:00", formatter);

        schedule.setDateStart(dtstart);
        schedule.setDateEnd(dtend);
        schedule.setRoom(room100);

        talk1.setSchedule(schedule);

//        Talk talk2 = new Talk("Talk 2");

        user1.addTalk(talk1);

        this.userRepository.save(user1);

        User user2 = new User("speak 2", "sp2", Role.SPEAKER);
        this.userRepository.save(user2);

        this.roomRepository.save(new Room("Room 120"));
        this.roomRepository.save(new Room("Room 125"));
        this.roomRepository.save(new Room("Room 240"));
    }

}