package nekrasova.svetlana.conference.restcontroller;

import nekrasova.svetlana.conference.entity.Room;
import nekrasova.svetlana.conference.repository.RoomRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestRoomController {
    private final RoomRepository roomRepository;

    RestRoomController(RoomRepository roomRepository){
        this.roomRepository = roomRepository;
    }

    @GetMapping("/rooms")
    List<Room> allRooms(){
        return roomRepository.findAll();
    }
}
