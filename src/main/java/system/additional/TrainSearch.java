package system.additional;

import system.model.Station;

import java.util.Date;

public class TrainSearch {

    private Station station1;
    private Station station2;
    private Date date;

    public TrainSearch() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Station getStation1() {
        return station1;
    }

    public void setStation1(Station station1) {
        this.station1 = station1;
    }

    public Station getStation2() {
        return station2;
    }

    public void setStation2(Station station2) {
        this.station2 = station2;
    }
}
