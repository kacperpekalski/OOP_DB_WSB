package pl.wsb.hotel;

import java.time.LocalDate;

import pl.wsb.hotel.client.Client;
import pl.wsb.hotel.client.PremiumClient;
import pl.wsb.hotel.client.PremiumClientType;
import pl.wsb.hotel.room.Room;
import pl.wsb.hotel.room.RoomReservation;
import pl.wsb.hotel.services.SpecialService;
import pl.wsb.hotel.services.LuggageService;
import pl.wsb.hotel.services.TimeService;

public class Main {
  public static void main(String[] args) {
    // Create example clients
    Client clientBasic = new Client("0", "Basic", "Client", LocalDate.of(1975, 2, 14),
        true, "man", "100200300", "basic.client@example.com");
    Client clientPremium = new PremiumClient("1", "Premium", "Client", LocalDate.of(2002, 1, 2),
        false, "woman", "400500600", "premium.client@example.com", PremiumClientType.PREMIUM);
    Client clientPremiumPlus = new PremiumClient("2", "Premiumplus", "Client", LocalDate.of(1960, 1, 2),
        false, "man", "700800900", "premiumplus.client@example.com", PremiumClientType.PREMIUM_PLUS);

    // Create example rooms
    Room roomForBasic = new Room("11", 10, 1, false, false, false, true);
    Room roomForPremium = new Room("22", 20, 2, false, true, false, true);
    Room roomForPremiumPlus = new Room("33", 30, 3, true, true, true, true);

    // Create example reservations
    RoomReservation roomReservationForBasic = new RoomReservation(LocalDate.now(),
        clientBasic, roomForBasic);
    roomReservationForBasic.confirmReservation();
    RoomReservation roomReservationForPremium = new RoomReservation(LocalDate.now(),
        clientPremium, roomForPremium);
    RoomReservation roomReservationForPremiumPlus = new RoomReservation(LocalDate.now(),
        clientPremiumPlus, roomForPremiumPlus);
    roomReservationForBasic.confirmReservation();

    // Create example special services
    SpecialService serviceLuggage = new LuggageService("Luggage storage", 5);
    SpecialService serviceTime = new TimeService("Current time info", 0);

    // Create an example empty hotel
    Hotel hotel = new Hotel("Example Hotel ******");
    System.out.println();
    System.out.println("======================== Empty hotel: ========================");
    System.out.println();
    hotel.prettyPrintSimple();

    // Fill the hotel
    hotel.addSpecialService(serviceLuggage);
    hotel.addSpecialService(serviceTime);
    hotel.addRoom(roomForBasic);
    hotel.addRoom(roomForPremium);
    hotel.addRoom(roomForPremiumPlus);
    hotel.addReservation(roomReservationForBasic);
    hotel.addReservation(roomReservationForPremium);
    hotel.addReservation(roomReservationForPremiumPlus);
    hotel.addClient(clientBasic);
    hotel.addClient(clientPremium);
    hotel.addClient(clientPremiumPlus);
    System.out.println();
    System.out.println("======================== Filled hotel: =======================");
    System.out.println();
    hotel.prettyPrintComplex();

    // Order services available in the hotel
    System.out.println();
    System.out.println("======================= Order services: ======================");
    System.out.println();
    hotel.getSpecialServices().forEach(serviceSpecial -> {
      System.out.println();
      serviceSpecial.prettyPrint();
      serviceSpecial.orderService();
    });

    // Partially empty the hotel
    hotel.removeSpecialService(serviceLuggage);
    hotel.removeRoom(roomForPremium);
    hotel.removeReservation(roomReservationForPremium);
    hotel.removeClient(clientPremium);
    System.out.println();
    System.out.println("================== Partially emptied hotel: ==================");
    System.out.println();
    hotel.prettyPrintComplex();
  }
}
