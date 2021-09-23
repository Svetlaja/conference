package nekrasova.svetlana.conference.restcontroller;

import nekrasova.svetlana.conference.entity.Room;
import nekrasova.svetlana.conference.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RestRoomController {
    @Autowired
    RoomService roomService;

    /*
     * Get list of all rooms
     */
    @GetMapping("/talk/rooms")
    public List<Room> allRooms() {
        return roomService.getAllRooms();
    }
}
