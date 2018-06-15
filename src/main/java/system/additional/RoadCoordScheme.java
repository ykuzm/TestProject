package system.additional;

public class RoadCoordScheme {

    int coordX1;
    int coordX2;
    int coordY1;
    int coordY2;

    public RoadCoordScheme() {
    }

    public int getCoordX1() {
        return coordX1;
    }

    public void setCoordX1(int coordX1) {
        this.coordX1 = coordX1;
    }

    public int getCoordX2() {
        return coordX2;
    }

    public void setCoordX2(int coordX2) {
        this.coordX2 = coordX2;
    }

    public int getCoordY1() {
        return coordY1;
    }

    public void setCoordY1(int coordY1) {
        this.coordY1 = coordY1;
    }

    public int getCoordY2() {
        return coordY2;
    }

    public void setCoordY2(int coordY2) {
        this.coordY2 = coordY2;
    }

    @Override
    public String toString() {
        return "RoadCoordScheme{" +
                "coordX1=" + coordX1 +
                ", coordX2=" + coordX2 +
                ", coordY1=" + coordY1 +
                ", coordY2=" + coordY2 +
                '}';
    }
}
