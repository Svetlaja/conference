package nekrasova.svetlana.conference.entityDto;

import nekrasova.svetlana.conference.entity.User;

public class SpeakerDto {
    private long speakerId;

    private String speakerName;

    public SpeakerDto() {
    }

    public SpeakerDto(long speakerId, String speakerName) {
        this.speakerId = speakerId;
        this.speakerName = speakerName;
    }

    public long getSpeakerId() {
        return speakerId;
    }

    public void setSpeakerId(long speakerId) {
        this.speakerId = speakerId;
    }

    public String getSpeakerName() {
        return speakerName;
    }

    public void setSpeakerName(String speakerName) {
        this.speakerName = speakerName;
    }

    public static SpeakerDto getFromUser(User user) {
        return new SpeakerDto(user.getId(), user.getUsername());
    }
}
