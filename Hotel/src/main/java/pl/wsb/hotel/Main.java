package pl.wsb.hotel;

import java.time.LocalDate;

public class Main
{

    public static void main(String[] args)
    {
        // Utworzenie klientów
        Client kowalski = new Client("01","Janusz","Kowalski",LocalDate.of(2000,4,23),true,"M","+48666999666","Janusz.Kow@gmail.com");
        Client brzeczyszczykiewicz = new Client("03","Adam","Brzeczyszczykiewicz",LocalDate.of(2004,4,23),true,"M","+48888999888","Adam.Brze@gmail.com");

        // Utworzenie pokojów
        Room room1 = new Room("12",23.5,2,true,true,false,true);
        Room room2 = new Room("13",23.5,2,true,true,false,true);
        // Dokonanie rezerwacji przez klienta kowalski na pokój room1
        RoomReservation roomReservation_12 = new RoomReservation(LocalDate.now(),kowalski,room1);

        // Test metod klasy RoomReservation

        System.out.println(roomReservation_12.getRoom().getId());
        System.out.println(roomReservation_12.getClient().getFullName());
        System.out.println(roomReservation_12.getDate());
        System.out.println(roomReservation_12.isConfirmed());

        System.out.println("------------");

        roomReservation_12.setDate(LocalDate.of(2024,4,3));
        roomReservation_12.setClient(brzeczyszczykiewicz);
        roomReservation_12.setRoom(room2);
        roomReservation_12.confirmReservation();

        System.out.println(roomReservation_12.getRoom().getId());
        System.out.println(roomReservation_12.getClient().getFullName());
        System.out.println(roomReservation_12.getDate());
        System.out.println(roomReservation_12.isConfirmed());

        System.out.println("------------");

        // Testy metod klasy Client

        System.out.println(kowalski.getId());
        System.out.println(kowalski.getFirstName());
        System.out.println(kowalski.getLastName());
        System.out.println(kowalski.getFullName());
        System.out.println(kowalski.getBirthDate());
        System.out.println(kowalski.getAge());
        System.out.println(kowalski.getSmoker());
        System.out.println(kowalski.getGender());
        System.out.println(kowalski.getNumber());
        System.out.println(kowalski.getEmail());

        System.out.println("------------");

        kowalski.setId("02");
        kowalski.setFirstName("Janina");
        kowalski.setLastName("Kowalska");
        kowalski.setBirthDate(LocalDate.of(2001,8,12));
        kowalski.setSmoker(false);
        kowalski.setGender("F");
        kowalski.setNumber("+48999666999");
        kowalski.setEmail("Janina.kow@gmail.com");

        System.out.println(kowalski.getId());
        System.out.println(kowalski.getFirstName());
        System.out.println(kowalski.getLastName());
        System.out.println(kowalski.getFullName());
        System.out.println(kowalski.getBirthDate());
        System.out.println(kowalski.getAge());
        System.out.println(kowalski.getSmoker());
        System.out.println(kowalski.getGender());
        System.out.println(kowalski.getNumber());
        System.out.println(kowalski.getEmail());

        System.out.println("------------");
        // Test metod klasy Room

        System.out.println(room1.getId());
        System.out.println(room1.getArea());
        System.out.println(room1.getFloor());
        System.out.println(room1.isHasBalcony());
        System.out.println(room1.isHasSafe());
        System.out.println(room1.isHasKingSizeBed());
        System.out.println(room1.isHasTV());

        System.out.println("------------");

        room1.setId("19");
        room1.setArea(59.9);
        room1.setFloor(3);
        room1.setHasBalcony(false);
        room1.setHasSafe(true);
        room1.setHasKingSizeBed(false);
        room1.setHasTV(false);

        System.out.println(room1.getId());
        System.out.println(room1.getArea());
        System.out.println(room1.getFloor());
        System.out.println(room1.isHasBalcony());
        System.out.println(room1.isHasSafe());
        System.out.println(room1.isHasKingSizeBed());
        System.out.println(room1.isHasTV());

        System.out.println("------------");
    }
}

