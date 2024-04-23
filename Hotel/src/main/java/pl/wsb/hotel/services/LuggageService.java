package pl.wsb.hotel.services;

public class LuggageService extends SpecialService {
  protected LuggageService(String name, double price, String description) {
    super(name, price, description);
  }

  @Override
  public void orderService() {
    // message stating that the hotel is storing client's luggage
    System.out.println("The hotel is storing your luggage");
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
