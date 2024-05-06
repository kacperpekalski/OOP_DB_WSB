package pl.wsb.hotel.room;

import java.time.LocalDate;
import pl.wsb.hotel.client.Client;

public class RoomReservation {
  private String id;
  private LocalDate date;
  private Client client;
  private Room room;
  private boolean isConfirmed;

  public RoomReservation(String id, LocalDate date, Client client, Room room) {
    this.id = id;
    this.date = date;
    this.client = client;
    this.room = room;
    this.isConfirmed = false;
  }

  public String getId() {
    return id;
  }

  public void setDate(String id) {
    this.id = id;
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

  public void prettyPrint() {
    System.out.println("  instance    : " + toString());
    System.out.println("  ID          : " + getId());
    System.out.println("  date        : " + getDate());
    System.out.println("  client ID   : " + getClient().getId());
    System.out.println("  room ID     : " + getRoom().getId());
    System.out.println("  confirmed   : " + isConfirmed());
  }
}
