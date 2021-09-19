package nekrasova.svetlana.conference.entity.enums;

public enum ScheduleTime {
    FIRST(1, "08:30 - 10:00"),
    SECOND(2, "10:30 - 12:00"),
    THIRD(3, "12:30 - 14:00"),
    FOURTH(4, "14:30 - 16:00"),
    FIFTH(5, "16:30 - 18:00");


    private int scheduleTimeID;
    private String scheduleTimeName;

    ScheduleTime(int scheduleTimeID, String scheduleTimeName) {
        this.scheduleTimeID = scheduleTimeID;
        this.scheduleTimeName = scheduleTimeName;
    }

    public int getScheduleTimeID() {
        return scheduleTimeID;
    }

    public void setScheduleTimeID(int scheduleTimeID) {
        this.scheduleTimeID = scheduleTimeID;
    }

    public String getScheduleTimeName() {
        return scheduleTimeName;
    }

    public void setScheduleTimeName(String scheduleTimeName) {
        this.scheduleTimeName = scheduleTimeName;
    }
}
