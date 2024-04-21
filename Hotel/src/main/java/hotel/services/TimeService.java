package hotel.services;

public class TimeService extends SpecialService {
  protected TimeService(String name, double price) {
    super(name, price);
  }

  @Override
  public void orderService() {
    // print current time
    System.out.println("Current time: " + java.time.LocalTime.now());
  }

  @Override
  public void checkAvailability() {
    // message that time service is available
    System.out.println("Time service is available");
  }

  @Override
  public void updatePrice(double newPrice) {
    // message that price has been updated
    System.out.println("Price has been updated");
  }

  @Override
  public double checkPrice() {
    return price;
  }

  @Override
  public void updateDescription(String newDescription) {
    description = newDescription;
  }

  @Override
  public String getDescription() {
    return description;
  }
}
