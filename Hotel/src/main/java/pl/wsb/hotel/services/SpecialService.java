package pl.wsb.hotel.services;

public abstract class SpecialService {
  protected String name;
  protected double price;
  protected boolean isAvailable;
  protected String description;

  protected SpecialService(String name, double price) {
    this.name = name;
    this.price = price;
    this.description = "";
  }

  public abstract void orderService();
  public abstract void checkAvailability();
  public abstract void updatePrice(double newPrice);
  public abstract double checkPrice();
  public abstract void updateDescription(String newDescription);
  public abstract String getDescription();
}
