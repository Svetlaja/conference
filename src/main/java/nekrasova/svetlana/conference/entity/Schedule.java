package nekrasova.svetlana.conference.entity;

import com.sun.istack.NotNull;
import nekrasova.svetlana.conference.entity.enums.ScheduleTime;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "schedule")

public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "date", nullable = false)
    @NotNull
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime  date;

    @Enumerated(EnumType.STRING)
    private ScheduleTime scheduleTime;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @OneToOne(optional = false, mappedBy = "schedule")
    private Talk talk;

    public Schedule() {
    }


}
