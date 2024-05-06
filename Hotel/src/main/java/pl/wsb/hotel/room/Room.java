package pl.wsb.hotel.room;

public class Room {
  private String id;
  private double area;
  private int floor;
  private boolean hasKingSizeBed;
  private boolean hasBalcony;
  private boolean hasSafe;
  private boolean hasTv;
  private String description;

  public Room(String id, double area, int floor, boolean hasKingSizeBed, String description) {
    this.id = id;
    this.area = area;
    this.floor = floor;
    this.hasKingSizeBed = hasKingSizeBed;
    this.description = description;
  }

  public Room(String id, double area, int floor, boolean hasKingSizeBed, boolean hasBalcony,
      boolean hasSafe, boolean hasTv, String description) {
    this.id = id;
    this.area = area;
    this.floor = floor;
    this.hasKingSizeBed = hasKingSizeBed;
    this.hasBalcony = hasBalcony;
    this.hasSafe = hasSafe;
    this.hasTv = hasTv;
    this.description = description;
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

  public boolean hasKingSizeBed() {
    return hasKingSizeBed;
  }

  public void setHasKingSizeBed(boolean hasKingSizeBed) {
    this.hasKingSizeBed = hasKingSizeBed;
  }

  public boolean hasBalcony() {
    return hasBalcony;
  }

  public void setHasBalcony(boolean hasBalcony) {
    this.hasBalcony = hasBalcony;
  }

  public boolean hasSafe() {
    return hasSafe;
  }

  public void setHasSafe(boolean hasSafe) {
    this.hasSafe = hasSafe;
  }

  public boolean hasTv() {
    return hasTv;
  }

  public void setHasTV(boolean hasTv) {
    this.hasTv = hasTv;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void prettyPrint() {
    System.out.println("  instance       : " + toString());
    System.out.println("  ID             : " + getId());
    System.out.println("  price          : " + getArea());
    System.out.println("  price          : " + getFloor());
    System.out.println("  king size bed  : " + hasKingSizeBed());
    System.out.println("  balcony        : " + hasBalcony());
    System.out.println("  safe           : " + hasSafe());
    System.out.println("  TV             : " + hasTv());
    System.out.println("  description    : " + getDescription());
  }
}
