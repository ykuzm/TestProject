package system.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "station_data")
public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "coordX", nullable = false)
    private int coordX;

    @Column(name = "coordY", nullable = false)
    private int coordY;

    public Station() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = stationNameFormat(name);
    }

    public int getCoordX() {
        return coordX;
    }

    public void setCoordX(int coordX) {
        this.coordX = coordX;
    }

    public int getCoordY() {
        return coordY;
    }

    public void setCoordY(int coordY) {
        this.coordY = coordY;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Station station = (Station) o;
        return getCoordX() == station.getCoordX() &&
                getCoordY() == station.getCoordY();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCoordX(), getCoordY());
    }

    @Override
    public String toString() {
        return "Station{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordX=" + coordX +
                ", coordY=" + coordY +
                '}';
    }

    /**
     * Method to convert station name to necessary format
     *
     * @param s s
     * @return station name in necessary format
     */
    public static String stationNameFormat(String s) {
        char symbols[] = s.toCharArray();
        symbols[0] = Character.toUpperCase(symbols[0]);
        for (int i = 1; i < symbols.length; i++) {
            if (Character.isLetter(symbols[i]) && Character.isLetter(symbols[i-1])) {
                symbols[i] = Character.toLowerCase(symbols[i]);
            }
            if (Character.isLetter(symbols[i]) && !Character.isLetter(symbols[i-1])) {
                symbols[i] = Character.toUpperCase(symbols[i]);
            }
        }
        return new String(symbols);
    }
}
