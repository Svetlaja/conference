package nekrasova.svetlana.conference.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "talk")
public class Talk {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "talk_id")
    private long id;

    private String talkName;

    @ManyToMany(mappedBy = "speakersTalks")
    private Set<User> speakers = new HashSet<>();

    @OneToOne(mappedBy = "talk", cascade = CascadeType.ALL)
    private Schedule schedule;

    public Talk() {
    }

    public Talk(String talkName) {
        this.talkName = talkName;
    }

    public void addSpeaker(User user) {
        this.speakers.add(user);
        user.getSpeakersTalks().add(this);
    }

    public void removeSpeaker(User user) {
        this.speakers.remove(user);
        user.getSpeakersTalks().remove(this);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTalkName() {
        return talkName;
    }

    public void setTalkName(String talkName) {
        this.talkName = talkName;
    }

    public Set<User> getSpeakers() {
        return speakers;
    }

    public void setSpeakers(Set<User> speakers) {
        this.speakers = speakers;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }
}
