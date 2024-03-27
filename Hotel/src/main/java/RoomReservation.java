import java.time.LocalDate;

public class RoomReservation {
    private LocalDate date;
    private Client client;
    private Room room;
    private boolean isConfirmed;

    public RoomReservation(LocalDate date, Client client, Room room) {
        this.date = date;
        this.client = client;
        this.room = room;
        this.isConfirmed = false;
    }

    public LocalDate getDate() {

        return date;
    }

    public void setDate(LocalDate date) {

        this.date = date;
    }

    public Client getClient() {

        return client;
    }

    public void setClient(Client client) {

        this.client = client;
    }

    public Room getRoom() {

        return room;
    }

    public void setRoom(Room room) {

        this.room = room;
    }

    public boolean isConfirmed() {

        return isConfirmed;
    }

    public void confirmReservation() {

        this.isConfirmed = true;
    }

}
