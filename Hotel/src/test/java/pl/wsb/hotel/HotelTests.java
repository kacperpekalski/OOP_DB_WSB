package pl.wsb.hotel;

import java.time.LocalDate;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.wsb.hotel.client.Client;
import pl.wsb.hotel.exceptions.ClientNotFoundException;
import pl.wsb.hotel.exceptions.RoomNotFoundException;
import pl.wsb.hotel.room.Room;

public class HotelTests {
  @Test
  public void newClientShouldBeCreatedAndAdded() {
    // given
    Hotel hotelUnderTests = new Hotel("Hotel under tests");
    final String givenClientFirstName = "Given";
    final String givenClientLastName = "Client";
    final LocalDate givenClientBirthDate = LocalDate.now().minusYears(84);

    // when
    final String receivedClientId =
        hotelUnderTests.addClient(givenClientFirstName, givenClientLastName, givenClientBirthDate);
    try {
      final Client receivedClient = hotelUnderTests.getClient(receivedClientId);

      // then
      Assertions.assertThat(receivedClient.getFirstName()).isEqualTo(givenClientFirstName);
      Assertions.assertThat(receivedClient.getLastName()).isEqualTo(givenClientLastName);
      Assertions.assertThat(receivedClient.getBirthDate()).isEqualTo(givenClientBirthDate);
    } catch (ClientNotFoundException exception) {
      Assertions.fail("Client ID " + receivedClientId + " not found", exception);
    }
  }

  @Test
  public void requestingNonexistentClientIdShouldThrowClientNotFoundException() {
    // given
    Hotel hotelUnderTests = new Hotel("Hotel under tests");
    String nonexistentClientId = "nonexistentClientId";

    // when
    Throwable thrown =
        Assertions.catchThrowable(() -> { hotelUnderTests.getClient(nonexistentClientId); });

    // then
    Assertions.assertThat(thrown).isInstanceOf(ClientNotFoundException.class);
  }

  @Test
  public void clientFullNameShouldBeProperlyReturned() {
    // given
    Hotel hotelUnderTests = new Hotel("Hotel under tests");
    final String givenClientFirstName = "Given";
    final String givenClientLastName = "Client";
    final LocalDate givenClientBirthDate = LocalDate.now().minusYears(84);

    // when
    final String receivedClientId =
        hotelUnderTests.addClient(givenClientFirstName, givenClientLastName, givenClientBirthDate);
    try {
      final String receivedFullName = hotelUnderTests.getClientFullName(receivedClientId);
      final Client receivedClient = hotelUnderTests.getClient(receivedClientId);

      // then
      Assertions.assertThat(receivedFullName).isEqualTo(receivedClient.getFullName());
    } catch (ClientNotFoundException exception) {
      Assertions.fail("Client ID " + receivedClientId + " not found", exception);
    }
  }

  @Test
  public void numberOfUnderageClientsShouldBeProperlyCalculated() {
    // given
    Hotel hotelUnderTests = new Hotel("Hotel under tests");
    final int givenNumberOfClientsUnderage = 3;
    for (int i = 0; i < givenNumberOfClientsUnderage; i++) {
      hotelUnderTests.addClient("Underage", Integer.toString(i), LocalDate.now().minusYears(17));
    }
    final int givenNumberOfClientsOfage = 6;
    for (int i = 0; i < givenNumberOfClientsOfage; i++) {
      hotelUnderTests.addClient("Of age", Integer.toString(i), LocalDate.now().minusYears(18));
    }

    // when
    final int receivedNumberOfUnderageClients = hotelUnderTests.getNumberOfUnderageClients();

    // then
    Assertions.assertThat(receivedNumberOfUnderageClients).isEqualTo(givenNumberOfClientsUnderage);
  }

  @Test
  public void newRoomShouldBeCreatedAndAdded() {
    // given
    Hotel hotelUnderTests = new Hotel("Hotel under tests");
    final double givenArea = 20;
    final int givenFloor = 2;
    final boolean givenHasKingSizeBed = true;
    final String givenDescription = "given room description";

    // when
    final String receivedRoomId =
        hotelUnderTests.addRoom(givenArea, givenFloor, givenHasKingSizeBed, givenDescription);
    try {
      final Room receivedRoom = hotelUnderTests.getRoom(receivedRoomId);

      // then
      Assertions.assertThat(receivedRoom.getArea()).isEqualTo(givenArea);
      Assertions.assertThat(receivedRoom.getFloor()).isEqualTo(givenFloor);
      Assertions.assertThat(receivedRoom.hasKingSizeBed()).isEqualTo(givenHasKingSizeBed);
      Assertions.assertThat(receivedRoom.getDescription()).isEqualTo(givenDescription);
    } catch (RoomNotFoundException exception) {
      Assertions.fail("Room ID " + receivedRoomId + " not found", exception);
    }
  }

  @Test
  public void requestingNonexistentRoomIdShouldThrowRoomNotFoundException() {
    // given
    Hotel hotelUnderTests = new Hotel("Hotel under tests");
    String nonexistentRoomId = "nonexistentRoomId";

    // when
    Throwable thrown =
        Assertions.catchThrowable(() -> { hotelUnderTests.getRoom(nonexistentRoomId); });

    // then
    Assertions.assertThat(thrown).isInstanceOf(RoomNotFoundException.class);
  }

  @Test
  public void roomAreaShouldBeProperlyReturned() {
    // given
    Hotel hotelUnderTests = new Hotel("Hotel under tests");
    final double givenArea = 20;
    final int givenFloor = 2;
    final boolean givenHasKingSizeBed = true;
    final String givenDescription = "given room description";

    // when
    final String receivedRoomId =
        hotelUnderTests.addRoom(givenArea, givenFloor, givenHasKingSizeBed, givenDescription);
    try {
      final double receivedRoomArea = hotelUnderTests.getRoomArea(receivedRoomId);
      final Room receivedRoom = hotelUnderTests.getRoom(receivedRoomId);

      // then
      Assertions.assertThat(receivedRoomArea).isEqualTo(receivedRoom.getArea());
    } catch (RoomNotFoundException exception) {
      Assertions.fail("Room ID " + receivedRoomId + " not found", exception);
    }
  }

  @Test
  public void numberOfRoomsWithKingSizeBedAtFloorShouldBeProperlyReturned() {
    // given
    Hotel hotelUnderTests = new Hotel("Hotel under tests");
    final double givenArea = 20;
    final int givenNumberOfRoomsWithoutKingSizeBedAtFloor1 = 2;
    for (int i = 0; i < givenNumberOfRoomsWithoutKingSizeBedAtFloor1; i++) {
      hotelUnderTests.addRoom(givenArea, 1, false, "At floor 1 without king size bed");
    }
    final int givenNumberOfRoomsWithKingSizeBedAtFloor1 = 7;
    for (int i = 0; i < givenNumberOfRoomsWithKingSizeBedAtFloor1; i++) {
      hotelUnderTests.addRoom(givenArea, 1, true, "At floor 1 with king size bed");
    }
    final int givenNumberOfRoomsWithoutKingSizeBedAtFloor2 = 5;
    for (int i = 0; i < givenNumberOfRoomsWithoutKingSizeBedAtFloor2; i++) {
      hotelUnderTests.addRoom(givenArea, 2, false, "At floor 2 without king size bed");
    }
    final int givenNumberOfRoomsWithKingSizeBedAtFloor2 = 4;
    for (int i = 0; i < givenNumberOfRoomsWithKingSizeBedAtFloor2; i++) {
      hotelUnderTests.addRoom(givenArea, 2, true, "At floor 2 with king size bed");
    }

    // when
    final int receivedNumberOfRoomsWithKingSizeBedAtFloor1 =
        hotelUnderTests.getNumberOfRoomsWithKingSizeBed(1);
    final int receivedNumberOfRoomsWithKingSizeBedAtFloor2 =
        hotelUnderTests.getNumberOfRoomsWithKingSizeBed(2);

    // then
    Assertions.assertThat(receivedNumberOfRoomsWithKingSizeBedAtFloor1)
        .isEqualTo(givenNumberOfRoomsWithKingSizeBedAtFloor1);
    Assertions.assertThat(receivedNumberOfRoomsWithKingSizeBedAtFloor2)
        .isEqualTo(givenNumberOfRoomsWithKingSizeBedAtFloor2);
  }

  @Test
  public void NewReservationShouldBeCreated() {
    // given
    final String firstName = "Jan";
    final String lastName = "Kowalski";
    final LocalDate birtDay = LocalDate.of(1993, 4, 12);
    final double roomArea = 22.5;
    final int roomFloor = 2;
    final boolean hasKingSizedBed = false;
    final String roomDescription = "Description for tested room";
    final int expectedUnconfirmedReservation = 1;
    LocalDate date = LocalDate.now();
    Hotel hotelUnderTests = new Hotel("Hotel under tests");
    String hotelClientId = hotelUnderTests.addClient(firstName, lastName, birtDay);
    String hotelRoomId = hotelUnderTests.addRoom(roomArea, roomFloor, hasKingSizedBed, roomDescription);

    //when
    try {
      hotelUnderTests.addNewReservation(hotelClientId, hotelRoomId, date);
      int unconfirmedReservation = hotelUnderTests.getNumberOfUnconfirmedReservation(date);


      // then
      Assertions.assertThat(unconfirmedReservation).isEqualTo(expectedUnconfirmedReservation);
    } catch (RoomNotFoundException exception) {
      Assertions.fail("Room ID " + hotelRoomId + " not found", exception);
    } catch (ClientNotFoundException exception) {
      Assertions.fail("Client ID " + hotelClientId + " not found", exception);
    }

  }

  @Test
  public void RoomReservationShouldbeConfirmed() {
    //given
    final String firstName = "Jan";
    final String lastName = "Kowalski";
    final LocalDate birtDay = LocalDate.of(1993, 4, 12);
    final double roomArea = 22.5;
    final int roomFloor = 2;
    final boolean hasKingSizedBed = false;
    final String roomDescription = "Description for tested room";


    Hotel hotelUnderTests = new Hotel("Hotel under tests");
    String hotelClientId = hotelUnderTests.addClient(firstName, lastName, birtDay);
    String hotelRoomId = hotelUnderTests.addRoom(roomArea, roomFloor, hasKingSizedBed, roomDescription);

    //when
    try {

      String hotelRoomReservationId =hotelUnderTests.addNewReservation(hotelClientId, hotelRoomId, LocalDate.of(2024,4,4));
      hotelUnderTests.confirmReservation(hotelRoomReservationId);
      Collection<String> receivedRoomId = hotelUnderTests.getRoomIdsReservedByClient(hotelClientId);
      String receivedFirstRoomId = receivedRoomId.iterator().next();
      //then

      Assertions.assertThat(hotelRoomId).isEqualTo(receivedFirstRoomId);
    }catch (RoomNotFoundException exception) {
      Assertions.fail("Room ID " + hotelRoomId + " not found", exception);
    } catch (ClientNotFoundException exception) {
      Assertions.fail("Client ID " + hotelClientId + " not found", exception);
    } catch (ReservationNotFoundException exception) {
      Assertions.fail("Client ID " + hotelClientId + " not found", exception);
    }

  }
}
