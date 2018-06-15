package system.additional;

import system.model.Station;

public class RoadAdd {

    private Station station1;

    private Station station2;

    public RoadAdd() {
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

    @Override
    public String toString() {
        return "RoadAdd{" +
                "station1=" + station1 +
                ", station2=" + station2 +
                '}';
    }
}
