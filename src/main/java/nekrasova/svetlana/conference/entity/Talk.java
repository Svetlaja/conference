package nekrasova.svetlana.conference.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "talk")
public class Talk {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String talkName;

    @ManyToMany(mappedBy = "speakersTalks")
    private Collection<User> speakers;

    @OneToOne(optional = false, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    public Talk() {
    }

    public Talk(String talkName, Collection<User> speakers) {
        this.talkName = talkName;
        this.speakers = speakers;
    }

    public Talk(String talkName, Collection<User> speakers, Schedule schedule) {
        this.talkName = talkName;
        this.speakers = speakers;
        this.schedule = schedule;
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

    public Collection<User> getSpeakers() {
        return speakers;
    }

    public void setSpeakers(Collection<User> speakers) {
        this.speakers = speakers;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }
}
