package nekrasova.svetlana.conference.entityDto;

import java.util.ArrayList;
import java.util.List;

public class ScheduleByRoomDto {
    private String roomName;
    private List<TalkFullDto> roomTalks = new ArrayList<>();

    public ScheduleByRoomDto() {
    }

    public ScheduleByRoomDto(String roomName, List<TalkFullDto> roomTalks) {
        this.roomName = roomName;
        this.roomTalks = roomTalks;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public List<TalkFullDto> getRoomTalks() {
        return roomTalks;
    }

    public void setRoomTalks(List<TalkFullDto> roomTalks) {
        this.roomTalks = roomTalks;
    }
}
