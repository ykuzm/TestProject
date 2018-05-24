package system.additional;

import system.model.Station;

import java.util.Date;

public class ScheduleAdd {

    private Station station;
    private Date date;

    public ScheduleAdd() {
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
