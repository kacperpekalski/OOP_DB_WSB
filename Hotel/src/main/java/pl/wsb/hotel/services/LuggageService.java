package pl.wsb.hotel.services;

public class LuggageService extends SpecialService {
  public LuggageService(String name, double price) {
    super(name, price);
  }

  @Override
  public void orderService() {
    // message stating that the hotel is storing client's luggage
    System.out.println("The hotel is storing your luggage");
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
