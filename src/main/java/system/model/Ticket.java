package system.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "ticket_data")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @Column(name = "userId", nullable = false)
    private int userId;

    @Column(name = "trainId", nullable = false)
    private int trainId;

    @Column(name = "stationId1", nullable = false)
    private int stationId1;

    @Column(name = "stationId2", nullable = false)
    private int stationId2;

    public Ticket() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTrainId() {
        return trainId;
    }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return getUserId() == ticket.getUserId() &&
                getTrainId() == ticket.getTrainId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getTrainId());
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", userId=" + userId +
                ", trainId=" + trainId +
                ", stationId1=" + stationId1 +
                ", stationId2=" + stationId2 +
                '}';
    }
}
