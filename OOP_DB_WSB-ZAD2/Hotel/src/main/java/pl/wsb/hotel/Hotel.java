package pl.wsb.hotel;

public class Hotel {
    private String name;
    private List<SpecialService> specialServices;
    private List<Client> clients;
    private List<Reservation> reservations;
    private List<Room> rooms;

    public Hotel(String name) {
        this.name = name;
        this.specialServices = new ArrayList<>();
        this.clients = new ArrayList<>();
        this.reservations = new ArrayList<>();
        this.rooms = new ArrayList<>();
    }

    // getter i setter dla name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
