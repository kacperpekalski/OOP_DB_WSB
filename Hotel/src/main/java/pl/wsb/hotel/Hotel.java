package pl.wsb.hotel;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import pl.wsb.hotel.client.Client;
import pl.wsb.hotel.exceptions.ClientNotFoundException;
import pl.wsb.hotel.exceptions.RoomNotFoundException;
import pl.wsb.hotel.room.Room;
import pl.wsb.hotel.room.RoomReservation;
import pl.wsb.hotel.services.SpecialService;

public class Hotel implements HotelCapability {
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

  public Client getClient(String clientId) throws ClientNotFoundException {
    Client client = getClients().get(clientId);
    if (client == null) {
      throw new ClientNotFoundException("Client ID " + clientId + "not found");
    }

    return client;
  }

  @Override
  public String addClient(String firstName, String lastName, LocalDate birthDate) {
    Client client = new Client(UUID.randomUUID().toString(), firstName, lastName, birthDate);
    if (getClients().containsKey(client.getId())) {
      throw new IllegalArgumentException(
          String.format("Client " + client.getId() + " already exists"));
    }

    this.clients.put(client.getId(), client);
    return client.getId();
  }

  public Client removeClient(String clientId) throws ClientNotFoundException {
    Client client = this.clients.remove(clientId);
    if (client == null) {
      throw new ClientNotFoundException("Client ID " + clientId + "not found");
    }

    return client;
  }

  // This method would propagate the exception to remain consistent
  // with other methods taking `*Id` as a parameter,
  // but this would conflict with the `interface HotelCapability` definition.
  // `null` will be returned if the exception is caught to avoid throwing an unchecked exception
  // in place where `clientId` can be a valid String and a possible ID
  // but doesn't exist as a key in the map.
  @Override
  public String getClientFullName(String clientId) {
    try {
      return getClient(clientId).getFullName();
    } catch (ClientNotFoundException exception) {
      return null;
    }
  }

  @Override
  public int getNumberOfUnderageClients() {
    int numberOfUnderageClients = 0;
    for (Client client : getClients().values()) {
      if (client.getAge() < 18) {
        numberOfUnderageClients++;
      }
    }

    return numberOfUnderageClients;
  }

  public Map<String, Room> getRooms() {
    return rooms;
  }

  public Room getRoom(String roomId) throws RoomNotFoundException {
    Room room = getRooms().get(roomId);
    if (room == null) {
      throw new RoomNotFoundException("Room ID " + roomId + " not found");
    }

    return room;
  }

  @Override
  public String addRoom(double area, int floor, boolean hasKingSizeBed, String description) {
    Room room = new Room(UUID.randomUUID().toString(), area, floor, hasKingSizeBed, description);
    if (getRooms().containsKey(room.getId())) {
      throw new IllegalArgumentException("Room " + room.getId() + " already exists");
    }
    this.rooms.put(room.getId(), room);

    return room.getId();
  }

  public Room removeRoom(String roomId) throws RoomNotFoundException {
    Room room = this.rooms.remove(roomId);
    if (room == null) {
      throw new RoomNotFoundException("Room ID " + room + " not found");
    }

    return room;
  }

  // This method would propagate the exception to remain consistent
  // with other methods taking `*Id` as a parameter,
  // but this would conflict with the `interface HotelCapability` definition.
  // `Double.NaN` will be returned if the exception is caught to avoid throwing
  // an unchecked exception in place where `roomId` can be a valid String and a possible ID
  // but doesn't exist as a key in the map.
  @Override
  public double getRoomArea(String roomId) {
    try {
      return getRoom(roomId).getArea();
    } catch (RoomNotFoundException exception) {
      return Double.NaN;
    }
  }

  @Override
  public int getNumberOfRoomsWithKingSizeBed(int floor) {
    int numberOfRoomsWithKingSizeBedAtFloor = 0;
    for (Room room : getRooms().values()) {
      if ((room.getFloor() == floor) && room.hasKingSizeBed()) {
        numberOfRoomsWithKingSizeBedAtFloor++;
      }
    }

    return numberOfRoomsWithKingSizeBedAtFloor;
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
