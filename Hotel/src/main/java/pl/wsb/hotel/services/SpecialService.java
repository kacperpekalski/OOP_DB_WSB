package pl.wsb.hotel.services;

public abstract class SpecialService {
  protected String name;
  protected double price;
  protected String description;

  protected SpecialService(String name, double price, String description) {
    this.name = name;
    this.price = price;
    this.description = description;
  }

  public abstract void orderService();
  public abstract void setPrice(double price);
  public abstract double getPrice();
  public abstract void setDescription(String description);
  public abstract String getDescription();
}
