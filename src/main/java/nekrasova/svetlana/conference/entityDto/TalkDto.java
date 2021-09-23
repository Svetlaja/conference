package nekrasova.svetlana.conference.entityDto;

import nekrasova.svetlana.conference.entity.Talk;

public class TalkDto {
    private long talkId;
    private String talkName;

    public TalkDto() {
    }

    public TalkDto(String talkName) {
        this.talkName = talkName;
    }

    public TalkDto(long talkId, String talkName) {
        this.talkId = talkId;
        this.talkName = talkName;
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

    public static TalkDto fromTalk(Talk talk){
        return new TalkDto(talk.getId(), talk.getTalkName());
    }
}
