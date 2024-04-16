package pl.wsb.hotel.services;

public abstract class SpecialService {
    private String name;
    private double price;
    private boolean isAvailable;


    protected SpecialService(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public abstract void orderService();

    public abstract void checkAvailability();

    public abstract void updatePrice(double newPrice);
}