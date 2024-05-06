package pl.wsb.hotel;

import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import pl.wsb.hotel.client.Client;
import pl.wsb.hotel.room.Room;
import pl.wsb.hotel.room.RoomReservation;
import pl.wsb.hotel.services.SpecialService;

public class Hotel {
  private String name;
  private final Set<SpecialService> specialServices;
  private final Map<String, Client> clients;
  private final Map<String, Room> rooms;
  private final Map<String, RoomReservation> reservations;

  public Hotel(String name) {
    this.name = name;

    this.specialServices = new HashSet<SpecialService>();
    this.clients = new HashMap<String, Client>();
    this.reservations = new HashMap<String, RoomReservation>();
    this.rooms = new HashMap<String, Room>();
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

  public Map<String, Client> getClients() {
    return clients;
  }

  public void addClient(Client client) {
    if (client == null) {
      throw new IllegalArgumentException("Client cannot be null");
    }
    if (client.getId() == null) {
      throw new IllegalArgumentException("Client ID cannot be null");
    }

    if (this.clients.containsKey(client.getId())) {
      throw new IllegalArgumentException(String.format("Client %s already exists", client.getId()));
    }

    this.clients.put(client.getId(), client);
  }

  public void removeClient(Client client) {
    if (client == null) {
      throw new IllegalArgumentException("Client cannot be null");
    }
    if (client.getId() == null) {
      throw new IllegalArgumentException("Client ID cannot be null");
    }

    if (this.clients.remove(client.getId()) == null) {
      throw new IllegalArgumentException("Client does not exist");
    }
  }

  public void addRoom(Room room) {
    if (room == null) {
      throw new IllegalArgumentException("Room cannot be null");
    }
    if (room.getId() == null) {
      throw new IllegalArgumentException("Room ID cannot be null");
    }

    if (this.rooms.containsKey(room.getId())) {
      throw new IllegalArgumentException(String.format("Room %s already exists",
          room.getId()));
    }

    this.rooms.put(room.getId(), room);
  }

  public void removeRoom(Room room) {
    if (room == null) {
      throw new IllegalArgumentException("Room cannot be null");
    }
    if (room.getId() == null) {
      throw new IllegalArgumentException("Room ID cannot be null");
    }

    if (this.rooms.remove(room.getId()) == null) {
      throw new IllegalArgumentException("Room does not exist");
    }
  }

  public Map<String, RoomReservation> getReservations() {
    return reservations;
  }

  public void addReservation(RoomReservation reservation) {
    if (reservation == null) {
      throw new IllegalArgumentException("Reservation cannot be null");
    }
    if (reservation.getId() == null) {
      throw new IllegalArgumentException("Reservation ID cannot be null");
    }

    if (this.reservations.containsKey(reservation.getId())) {
      throw new IllegalArgumentException(String.format("Reservation %s already exists",
          reservation.getId()));
    }

    this.reservations.put(reservation.getId(), reservation);
  }

  public void removeReservation(RoomReservation reservation) {
    if (reservation == null) {
      throw new IllegalArgumentException("Reservation cannot be null");
    }
    if (reservation.getId() == null) {
      throw new IllegalArgumentException("Reservation ID cannot be null");
    }

    if (this.reservations.remove(reservation.getId()) == null) {
      throw new IllegalArgumentException("Reservation does not exist");
    }
  }

  public Map<String, Room> getRooms() {
    return rooms;
  }

  public void prettyPrintSimple() {
    System.out.println("Hotel:");
    System.out.println("  instance               : " + toString());
    System.out.println("  name                   : " + getName());
    System.out.println("  special service count  : " + getSpecialServices().size());
    System.out.println("  room count             : " + getRooms().size());
    System.out.println("  client count           : " + getClients().size());
    System.out.println("  reservation count      : " + getReservations().size());
  }

  public void prettyPrintComplex() {
    prettyPrintSimple();

    System.out.println();
    System.out.println("Special services in hotel:");
    getSpecialServices().forEach(serviceSpecial -> {
      System.out.println();
      serviceSpecial.prettyPrint();
    });

    System.out.println();
    System.out.println("Rooms in hotel:");
    getRooms().forEach((roomId, room) -> {
      System.out.println();
      room.prettyPrint();
    });

    System.out.println();
    System.out.println("Clients in hotel:");
    getClients().forEach((clientId, client) -> {
      System.out.println();
      client.prettyPrint();
    });

    System.out.println();
    System.out.println("Reservations in hotel:");
    getReservations().forEach((reservationId, reservation) -> {
      System.out.println();
      reservation.prettyPrint();
    });
  }
}
