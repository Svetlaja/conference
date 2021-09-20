package nekrasova.svetlana.conference.entity;

import javax.persistence.*;
import java.util.Collection;
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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "schedule_id", referencedColumnName = "schedule_id")
    private Schedule schedule;

    public Talk() {
    }

    public Talk(String talkName) {
        this.talkName = talkName;

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
