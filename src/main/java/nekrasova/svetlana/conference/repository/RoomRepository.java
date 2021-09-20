package nekrasova.svetlana.conference.repository;

import nekrasova.svetlana.conference.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
    Room findByRoomName(String roomName);
}
