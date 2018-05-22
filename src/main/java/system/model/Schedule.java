package system.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "schedule_data")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private int id;

    @Column(name = "trainId")
    private int trainId;

    @Column(name = "stationId")
    private int stationId;

    @Column(name = "arrivalTime")
    private Date arrivalTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTrainId() {
        return trainId;
    }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }

    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "id=" + id +
                ", trainId=" + trainId +
                ", stationId=" + stationId +
                ", arrivalTime=" + arrivalTime +
                '}';
    }
}
