package pl.wsb.hotel;

import java.util.HashSet;
import java.util.Set;
import pl.wsb.hotel.client.Client;
import pl.wsb.hotel.room.Room;
import pl.wsb.hotel.room.RoomReservation;
import pl.wsb.hotel.services.SpecialService;

public class Hotel {
  private String name;
  private final Set<SpecialService> specialServices;
  private final Set<Client> clients;
  private final Set<RoomReservation> reservations;
  private final Set<Room> rooms;

  public Hotel(String name) {
    this.name = name;

    this.specialServices = new HashSet<>();
    this.clients = new HashSet<>();
    this.reservations = new HashSet<>();
    this.rooms = new HashSet<>();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Set<SpecialService> getSpecialServices() {
    return specialServices;
  }

  public void addSpecialService(SpecialService service) {
    if (service == null) {
      throw new IllegalArgumentException("Special service cannot be null");
    }

    if (!this.specialServices.add(service)) {
      throw new IllegalArgumentException("Special service already exists");
    }
  }

  public void removeSpecialService(SpecialService service) {
    if (service == null) {
      throw new IllegalArgumentException("Special service cannot be null");
    }

    if (!this.specialServices.remove(service)) {
      throw new IllegalArgumentException("Special service does not exist");
    }
  }

  public Set<Client> getClients() {
    return clients;
  }

  public void addClient(Client client) {
    if (client == null) {
      throw new IllegalArgumentException("Client cannot be null");
    }

    if (!this.clients.add(client)) {
      throw new IllegalArgumentException("Client already exists");
    }
  }

  public void removeClient(Client client) {
    if (client == null) {
      throw new IllegalArgumentException("Client cannot be null");
    }

    if (!this.clients.remove(client)) {
      throw new IllegalArgumentException("Client does not exist");
    }
  }

  public Set<RoomReservation> getReservations() {
    return reservations;
  }

  public void addReservation(RoomReservation reservation) {
    if (reservation == null) {
      throw new IllegalArgumentException("Reservation cannot be null");
    }

    if (!this.reservations.add(reservation)) {
      throw new IllegalArgumentException("Reservation already exists");
    }
  }

  public void removeReservation(RoomReservation reservation) {
    if (reservation == null) {
      throw new IllegalArgumentException("Reservation cannot be null");
    }

    if (!this.reservations.remove(reservation)) {
      throw new IllegalArgumentException("Reservation does not exist");
    }
  }

  public Set<Room> getRooms() {
    return rooms;
  }

  public void addRoom(Room room) {
    if (room == null) {
      throw new IllegalArgumentException("Room cannot be null");
    }

    if (!this.rooms.add(room)) {
      throw new IllegalArgumentException("Room already exists");
    }
  }

  public void removeRoom(Room room) {
    if (room == null) {
      throw new IllegalArgumentException("Room cannot be null");
    }

    if (!this.rooms.remove(room)) {
      throw new IllegalArgumentException("Room does not exist");
    }
  }
}
