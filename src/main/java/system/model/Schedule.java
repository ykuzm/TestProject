package system.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "schedule_data")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @Column(name = "trainId", nullable = false)
    private int trainId;

    @Column(name = "stationId", nullable = false)
    private int stationId;

    @Column(name = "departureDate", nullable = false)
    private Date departureDate;

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

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Schedule schedule = (Schedule) o;
        return getTrainId() == schedule.getTrainId() &&
                getStationId() == schedule.getStationId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTrainId(), getStationId());
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "id=" + id +
                ", trainId=" + trainId +
                ", stationId=" + stationId +
                ", departureDate=" + departureDate +
                '}';
    }
}
