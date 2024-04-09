package pl.wsb.hotel;

public class SpecialService {
    private String name;

    public abstract void orderService();

    // dodatkowe metody abstrakcyjne
    public abstract void additionalMethod1();
    public abstract String additionalMethod2(int param);
    public abstract boolean additionalMethod3(String param, int param2);

    // getter i setter dla name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
