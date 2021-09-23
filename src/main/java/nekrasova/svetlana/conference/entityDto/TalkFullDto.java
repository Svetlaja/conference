package nekrasova.svetlana.conference.entityDto;

import nekrasova.svetlana.conference.entity.Talk;
import nekrasova.svetlana.conference.entity.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TalkFullDto {
    private long talkId;

    private String talkName;

    private LocalDateTime dateStart;

    private LocalDateTime dateEnd;

    private String roomName;

    private List<SpeakerDto> speakersList = new ArrayList<>();

    public TalkFullDto() {
    }

    public long getTalkId() {
        return talkId;
    }

    public void setTalkId(long talkId) {
        this.talkId = talkId;
    }

    public String getTalkName() {
        return talkName;
    }

    public void setTalkName(String talkName) {
        this.talkName = talkName;
    }

    public LocalDateTime getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDateTime dateStart) {
        this.dateStart = dateStart;
    }

    public LocalDateTime getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDateTime dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public List<SpeakerDto> getSpeakersList() {
        return speakersList;
    }

    public void setSpeakersList(List<SpeakerDto> speakersList) {
        this.speakersList = speakersList;
    }

    public static TalkFullDto fromTalk(Talk talk) {
        TalkFullDto talkDto = new TalkFullDto();
        talkDto.setTalkId(talk.getId());
        talkDto.setTalkName(talk.getTalkName());
        if (talk.getSchedule() != null) {
            talkDto.setDateStart(talk.getSchedule().getDateStart());
            talkDto.setDateEnd(talk.getSchedule().getDateEnd());
            talkDto.setRoomName(talk.getSchedule().getRoom().getRoomName());
        }
        List<SpeakerDto> speakerDtos = new ArrayList<>();
        for (User user : talk.getSpeakers()) {
            speakerDtos.add(SpeakerDto.getFromUser(user));
        }

        talkDto.setSpeakersList(speakerDtos);

        return talkDto;

    }
}

