package hotel.services;

public class LuggageService extends SpecialService {
    protected LuggageService(String name, double price) {
        super(name, price);
    }

    @Override
    public void orderService() {
        // message that hotel is stored client's luggage
        System.out.println("Hotel is storing your luggage");
    }

    @Override
    public void checkAvailability() {
        // message that luggage service is available
        System.out.println("Luggage service is available");
    }

    @Override
    public void updatePrice(double newPrice) {
        // message that price has been updated
        System.out.println("Price has been updated");
    }

    @Override
    public double checkPrice()
    {
        return price;
    }

    @Override
    public void updateDescription(String newDescription)
    {
        this.description = newDescription;
    }

    @Override
    public String getDescription()
    {
        return description;
    }
}
