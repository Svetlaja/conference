package nekrasova.svetlana.conference.entityDto;

import nekrasova.svetlana.conference.entity.Schedule;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class ScheduleDto {
    private long scheduleId;

    private String scheduleRoomName;
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime dateStart;
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime dateEnd;

    public ScheduleDto() {
    }

    public ScheduleDto(String scheduleRoomName, LocalDateTime dateStart, LocalDateTime dateEnd) {
        this.scheduleRoomName = scheduleRoomName;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }

    public long getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(long scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getScheduleRoomName() {
        return scheduleRoomName;
    }

    public void setScheduleRoomName(String scheduleRoomName) {
        this.scheduleRoomName = scheduleRoomName;
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


    public static ScheduleDto getFromSchedule(Schedule schedule) {
        ScheduleDto scheduleDto = new ScheduleDto();
        scheduleDto.setScheduleId(schedule.getId());
        scheduleDto.setScheduleRoomName(schedule.getRoom().getRoomName());
        scheduleDto.setDateStart(schedule.getDateStart());
        scheduleDto.setDateEnd(schedule.getDateEnd());

        return scheduleDto;
    }
}
