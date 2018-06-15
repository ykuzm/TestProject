package system.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "road_data")
public class Road {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @Column(name = "stationId1", nullable = false)
    private int stationId1;

    @Column(name = "stationId2", nullable = false)
    private int stationId2;

    @Column(name = "distance", nullable = false)
    private double distance;

    public Road() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStationId1() {
        return stationId1;
    }

    public void setStationId1(int stationId1) {
        this.stationId1 = stationId1;
    }

    public int getStationId2() {
        return stationId2;
    }

    public void setStationId2(int stationId2) {
        this.stationId2 = stationId2;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Road road = (Road) o;
        return getStationId1() == road.getStationId1() &&
                getStationId2() == road.getStationId2();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStationId1(), getStationId2());
    }

    @Override
    public String toString() {
        return "Road{" +
                "id=" + id +
                ", stationId1=" + stationId1 +
                ", stationId2=" + stationId2 +
                ", distance=" + distance +
                '}';
    }
}
