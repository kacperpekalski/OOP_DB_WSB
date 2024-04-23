package pl.wsb.hotel.services;

public abstract class SpecialService {
  protected String name;
  protected double price;

  protected SpecialService(String name, double price) {
    this.name = name;
    this.price = price;
  }

  public abstract void orderService();
  public abstract void setName(String name);
  public abstract String getName();
  public abstract void setPrice(double price);
  public abstract double getPrice();
}
