package nekrasova.svetlana.conference.service;

import nekrasova.svetlana.conference.entity.Room;
import nekrasova.svetlana.conference.entity.Schedule;
import nekrasova.svetlana.conference.entityDto.ScheduleByRoomDto;
import nekrasova.svetlana.conference.entityDto.ScheduleDto;
import nekrasova.svetlana.conference.entityDto.TalkFullDto;
import nekrasova.svetlana.conference.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleService {
    @Autowired
    ScheduleRepository scheduleRepository;
    @Autowired
    RoomService roomService;

    public List<ScheduleDto> findAll() {
        List<ScheduleDto> scheduleDtos = new ArrayList<>();
        for (Schedule schedule : scheduleRepository.findAll()) {
            scheduleDtos.add(ScheduleDto.getFromSchedule(schedule));
        }
        return scheduleDtos;
    }

    public List<TalkFullDto> findByRoom(Room room) {
        List<TalkFullDto> talkFullDtos = new ArrayList<>();
        for (Schedule schedule : scheduleRepository.findByRoom(room, Sort.by(Sort.Direction.ASC, "dateStart"))) {
            talkFullDtos.add(TalkFullDto.fromTalk(schedule.getTalk()));
        }
        if (talkFullDtos.size() > 0) {
            return talkFullDtos;
        }
        return null;
    }

    public List<ScheduleByRoomDto> getFullSchedule() {
        List<ScheduleByRoomDto> scheduleByRoomDtos = new ArrayList<>();

        for (Room room : roomService.getAllRooms()) {
            List<TalkFullDto> talkFullDtos = findByRoom(room);
            if (talkFullDtos != null) {
                scheduleByRoomDtos.add(new ScheduleByRoomDto(room.getRoomName(), talkFullDtos));
            }
        }
        return scheduleByRoomDtos;
    }
}
