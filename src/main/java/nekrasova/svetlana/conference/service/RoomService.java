package nekrasova.svetlana.conference.service;

import nekrasova.svetlana.conference.entity.Room;
import nekrasova.svetlana.conference.entity.Schedule;
import nekrasova.svetlana.conference.repository.RoomRepository;
import nekrasova.svetlana.conference.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RoomService {
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    ScheduleRepository scheduleRepository;

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public Room findByRoomName(String roomName) {
        return roomRepository.findByRoomName(roomName);
    }

    public boolean roomAvailability(Room room, LocalDateTime dateStart, LocalDateTime dateEnd) {
        for (Schedule schedule : scheduleRepository.findByRoom(room, Sort.by(Sort.Direction.ASC, "dateStart"))) {
            if (schedule.getDateStart().isBefore(dateEnd) && dateStart.isBefore(schedule.getDateEnd())) {
                return false;
            }
        }
        return true;
    }
}
