package system.additional;

import system.model.Station;
import system.model.Train;

import java.util.Date;

public class TrainAdd {

    private Train train;

    private Station station1;

    private Station station2;

    private Date departureDate;

    public TrainAdd() {
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
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

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    @Override
    public String toString() {
        return "TrainAdd{" +
                "train=" + train +
                ", station1=" + station1 +
                ", station2=" + station2 +
                ", departureDate=" + departureDate +
                '}';
    }
}
