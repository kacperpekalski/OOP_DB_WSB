package pl.wsb.hotel.services;

public class TimeService extends SpecialService {
  protected TimeService(String name, double price, String description) {
    super(name, price, description);
  }

  @Override
  public void orderService() {
    // print the current time
    System.out.println("Current time: " + java.time.LocalTime.now());
  }

  @Override
  public void setPrice(double price) {
    this.price = price;
  }

  @Override
  public double getPrice() {
    return price;
  }

  @Override
  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public String getDescription() {
    return description;
  }
}
