package hotel;

public class Room {
    private String id;
    private double area;
    private int floor;
    private boolean hasKingSizeBed;
    // Pola dodane przez nas
    private boolean hasBalcony;
    private boolean hasSafe;
    private boolean hasTV;

    public Room(String id, double area, int floor, boolean hasKingSizeBed, boolean hasBalcony, boolean hasSafe, boolean hasTV) {
        this.id = id;
        this.area = area;
        this.floor = floor;
        this.hasKingSizeBed = hasKingSizeBed;
        this.hasBalcony = hasBalcony;
        this.hasSafe = hasSafe;
        this.hasTV = hasTV;
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {

        this.id = id;
    }

    public double getArea() {

        return area;
    }

    public void setArea(double area) {

        this.area = area;
    }

    public int getFloor() {

        return floor;
    }

    public void setFloor(int floor) {

        this.floor = floor;
    }

    public boolean isHasKingSizeBed() {

        return hasKingSizeBed;
    }

    public void setHasKingSizeBed(boolean hasKingSizeBed) {

        this.hasKingSizeBed = hasKingSizeBed;
    }

    public boolean isHasBalcony() {

        return hasBalcony;
    }

    public void setHasBalcony(boolean hasBalcony) {

        this.hasBalcony = hasBalcony;
    }

    public boolean isHasSafe() {

        return hasSafe;
    }

    public void setHasSafe(boolean hasSafe) {

        this.hasSafe = hasSafe;
    }

    public boolean isHasTV() {

        return hasTV;
    }

    public void setHasTV(boolean hasTV) {

        this.hasTV = hasTV;
    }
}
