package system.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ticket_data")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private int id;

    @Column(name = "passengerId")
    private int passengerId;

    @Column(name = "trainId")
    private int trainId;

    public Ticket() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(int passengerId) {
        this.passengerId = passengerId;
    }

    public int getTrainId() {
        return trainId;
    }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", passengerId=" + passengerId +
                ", trainId=" + trainId +
                '}';
    }
}
