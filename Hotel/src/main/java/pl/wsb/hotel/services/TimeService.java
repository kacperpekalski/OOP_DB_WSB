package pl.wsb.hotel.services;

public class TimeService extends SpecialService {
  public TimeService(String name, double price) {
    super(name, price);
  }

  @Override
  public void orderService() {
    // print the current time
    System.out.println("Current time: " + java.time.LocalTime.now());
  }

  @Override
  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public void setPrice(double price) {
    this.price = price;
  }

  @Override
  public double getPrice() {
    return price;
  }
}
