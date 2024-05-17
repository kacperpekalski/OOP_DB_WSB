package pl.wsb.hotel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.swing.text.html.HTMLDocument.HTMLReader.SpecialAction;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.wsb.hotel.client.Client;
import pl.wsb.hotel.exceptions.ClientNotFoundException;
import pl.wsb.hotel.exceptions.ReservationNotFoundException;
import pl.wsb.hotel.exceptions.RoomNotFoundException;
import pl.wsb.hotel.exceptions.RoomReservedException;
import pl.wsb.hotel.room.Room;
import pl.wsb.hotel.room.RoomReservation;
import pl.wsb.hotel.services.LuggageService;
import pl.wsb.hotel.services.SpecialService;
import pl.wsb.hotel.services.TimeService;

public class HotelTests {
  @Test
  public void specialServiceShouldBeAdded() {
    // given
    final Hotel hotelUnderTests = new Hotel("Hotel under tests");
    final SpecialService givenLuggageService = new LuggageService("luggage service", 5);
    final SpecialService givenTimeService = new TimeService("time service", 0);

    // when
    hotelUnderTests.addSpecialService(givenLuggageService);
    hotelUnderTests.addSpecialService(givenTimeService);
    final Collection<SpecialService> receivedSpecialServices = hotelUnderTests.getSpecialServices();

    // then
    Assertions.assertThat(receivedSpecialServices)
        .containsOnly(givenLuggageService, givenTimeService);
  }

  @Test
  public void specialServiceShouldBeRemovable() {
    // given
    final Hotel hotelUnderTests = new Hotel("Hotel under tests");
    final SpecialService givenLuggageService = new LuggageService("luggage service", 5);
    hotelUnderTests.addSpecialService(givenLuggageService);

    // when
    hotelUnderTests.removeSpecialService(givenLuggageService);
    final Collection<SpecialService> receivedSpecialServices = hotelUnderTests.getSpecialServices();

    // then
    Assertions.assertThat(receivedSpecialServices).doesNotContain(givenLuggageService);
  }

  @Test
  public void newClientShouldBeCreatedAndAdded() {
    // given
    final Hotel hotelUnderTests = new Hotel("Hotel under tests");
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
    } catch (Exception exception) {
      Assertions.fail("Unexpected exception thrown", exception);
    }
  }

  @Test
  public void requestingNonexistentClientIdShouldThrowClientNotFoundException() {
    // given
    final Hotel hotelUnderTests = new Hotel("Hotel under tests");
    final String givenNonexistentClientId = "nonexistentClientId";

    // when
    final Throwable receivedNonexistentClientRequestedException =
        Assertions.catchThrowable(() -> { hotelUnderTests.getClient(givenNonexistentClientId); });

    // then
    Assertions.assertThat(receivedNonexistentClientRequestedException)
        .isInstanceOf(ClientNotFoundException.class);
  }

  @Test
  public void clientShouldBeRemovable() {
    // given
    final Hotel hotelUnderTests = new Hotel("Hotel under tests");
    final String givenClientId =
        hotelUnderTests.addClient("Given", "Client", LocalDate.now().minusYears(4));

    // when
    final Throwable receivedRequestedClientRemovalException =
        Assertions.catchThrowable(() -> { hotelUnderTests.removeClient(givenClientId); });
    final Throwable receivedRequestedRemovedClientException =
        Assertions.catchThrowable(() -> { hotelUnderTests.getClient(givenClientId); });

    // then
    Assertions.assertThat(receivedRequestedClientRemovalException).isEqualTo(null);
    Assertions.assertThat(receivedRequestedRemovedClientException)
        .isInstanceOf(ClientNotFoundException.class);
  }

  @Test
  public void removingNonexistentClientShouldThrowClientNotFoundException() {
    // given
    final Hotel hotelUnderTests = new Hotel("Hotel under tests");
    final String givenNonexistentClientId = "nonexistentClientId";

    // when
    final Throwable receivedRequestedNonexistentClientRemovalException = Assertions.catchThrowable(
        () -> { hotelUnderTests.removeClient(givenNonexistentClientId); });

    // then
    Assertions.assertThat(receivedRequestedNonexistentClientRemovalException)
        .isInstanceOf(ClientNotFoundException.class);
  }

  @Test
  public void clientFullNameShouldBeProperlyReturned() {
    // given
    final Hotel hotelUnderTests = new Hotel("Hotel under tests");
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
    } catch (Exception exception) {
      Assertions.fail("Unexpected exception thrown", exception);
    }
  }

  @Test
  public void numberOfUnderageClientsShouldBeProperlyCalculated() {
    // given
    final Hotel hotelUnderTests = new Hotel("Hotel under tests");
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
    final Hotel hotelUnderTests = new Hotel("Hotel under tests");
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
    } catch (Exception exception) {
      Assertions.fail("Unexpected exception thrown", exception);
    }
  }

  @Test
  public void requestingNonexistentRoomIdShouldThrowRoomNotFoundException() {
    // given
    final Hotel hotelUnderTests = new Hotel("Hotel under tests");
    final String nonexistentRoomId = "nonexistentRoomId";

    // when
    final Throwable thrown =
        Assertions.catchThrowable(() -> { hotelUnderTests.getRoom(nonexistentRoomId); });

    // then
    Assertions.assertThat(thrown).isInstanceOf(RoomNotFoundException.class);
  }

  @Test
  public void roomShouldBeRemovable() {
    // given
    final Hotel hotelUnderTests = new Hotel("Hotel under tests");
    final String givenRoomId = hotelUnderTests.addRoom(1, 2, false, "given room");

    // when
    final Throwable receivedRequestedRoomRemovalException =
        Assertions.catchThrowable(() -> { hotelUnderTests.removeRoom(givenRoomId); });
    final Throwable receivedRequestedRemovedRoomException =
        Assertions.catchThrowable(() -> { hotelUnderTests.getRoom(givenRoomId); });

    // then
    Assertions.assertThat(receivedRequestedRoomRemovalException).isEqualTo(null);
    Assertions.assertThat(receivedRequestedRemovedRoomException)
        .isInstanceOf(RoomNotFoundException.class);
  }

  @Test
  public void removingNonexistentRoomShouldThrowRoomNotFoundException() {
    // given
    final Hotel hotelUnderTests = new Hotel("Hotel under tests");
    final String givenNonexistentRoomId = "nonexistentRoomId";

    // when
    final Throwable receivedRequestedNonexistentRoomRemovalException =
        Assertions.catchThrowable(() -> { hotelUnderTests.removeRoom(givenNonexistentRoomId); });

    // then
    Assertions.assertThat(receivedRequestedNonexistentRoomRemovalException)
        .isInstanceOf(RoomNotFoundException.class);
  }

  @Test
  public void roomAreaShouldBeProperlyReturned() {
    // given
    final Hotel hotelUnderTests = new Hotel("Hotel under tests");
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
    } catch (Exception exception) {
      Assertions.fail("Unexpected exception thrown", exception);
    }
  }

  @Test
  public void numberOfRoomsWithKingSizeBedAtFloorShouldBeProperlyReturned() {
    // given
    final Hotel hotelUnderTests = new Hotel("Hotel under tests");
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
  public void newReservationShouldBeCreatedAndAdded() {
    // given
    final Hotel hotelUnderTests = new Hotel("Hotel under tests");
    final String givenClientId =
        hotelUnderTests.addClient("Given", "Client", LocalDate.now().minusYears(20));
    Client givenClient = null;
    try {
      givenClient = hotelUnderTests.getClient(givenClientId);
    } catch (Exception exception) {
      Assertions.fail("Unexpected exception thrown", exception);
    }
    final String givenRoomId = hotelUnderTests.addRoom(1, 2, false, "Given room");
    Room givenRoom = null;
    try {
      givenRoom = hotelUnderTests.getRoom(givenRoomId);
    } catch (Exception exception) {
      Assertions.fail("Unexpected exception thrown", exception);
    }
    final LocalDate givenReservationDate = LocalDate.now();
    final boolean givenReservationIsConfirmed = false;

    // when
    String receivedReservationId = null;
    try {
      receivedReservationId =
          hotelUnderTests.addNewReservation(givenClientId, givenRoomId, givenReservationDate);
    } catch (Exception exception) {
      Assertions.fail("Unexpected exception thrown", exception);
    }

    RoomReservation receivedReservation = null;
    try {
      receivedReservation = hotelUnderTests.getReservation(receivedReservationId);
    } catch (Exception exception) {
      Assertions.fail("Unexpected exception thrown", exception);
    }
    final Client receivedClient = receivedReservation.getClient();
    final Room receivedRoom = receivedReservation.getRoom();
    final LocalDate receivedReservationDate = receivedReservation.getDate();
    final boolean receivedReservationIsConfirmed = receivedReservation.isConfirmed();

    // then
    Assertions.assertThat(receivedClient).isEqualTo(givenClient);
    Assertions.assertThat(receivedRoom).isEqualTo(givenRoom);
    Assertions.assertThat(receivedReservationDate).isEqualTo(givenReservationDate);
    Assertions.assertThat(receivedReservationIsConfirmed).isEqualTo(givenReservationIsConfirmed);
  }

  @Test
  public void newReservationWithNonexistentIdsPassedShouldThrowNotFoundException() {
    // given
    final Hotel hotelUnderTests = new Hotel("Hotel under tests");
    final String givenClientIdNonexistent = "nonexistentClientId";
    final String givenClientIdExistent =
        hotelUnderTests.addClient("Given", "Client", LocalDate.now().minusYears(88));
    final String givenRoomIdNonexistent = "nonexistentRoomId";
    final String givenRoomIdExistent = hotelUnderTests.addRoom(7.7, 2, true, "Given room");
    final LocalDate givenReservationDate = LocalDate.now();

    // when
    final Throwable receivedClientIdNonexistentException = Assertions.catchThrowable(() -> {
      hotelUnderTests.addNewReservation(
          givenClientIdNonexistent, givenRoomIdExistent, givenReservationDate);
    });
    final Throwable receivedRoomIdNonexistentException = Assertions.catchThrowable(() -> {
      hotelUnderTests.addNewReservation(
          givenClientIdExistent, givenRoomIdNonexistent, givenReservationDate);
    });

    // then
    Assertions.assertThat(receivedClientIdNonexistentException)
        .isInstanceOf(ClientNotFoundException.class);
    Assertions.assertThat(receivedRoomIdNonexistentException)
        .isInstanceOf(RoomNotFoundException.class);
  }

  @Test
  public void newReservationForRoomAlreadyReservedOnDateShouldThrowRoomReservedException() {
    // given
    final Hotel hotelUnderTests = new Hotel("Hotel under tests");
    final String givenClientId =
        hotelUnderTests.addClient("Client", "0", LocalDate.now().minusYears(77));
    final String givenRoomIdExpected = hotelUnderTests.addRoom(7.7, 2, true, "Expected room");
    final String givenRoomIdUnexpected = hotelUnderTests.addRoom(8.8, 3, false, "Unexpected room");
    final LocalDate givenReservationDateExpected = LocalDate.now();
    final LocalDate givenReservationDateUnexpected = givenReservationDateExpected.plusDays(1);

    try {
      hotelUnderTests.addNewReservation(
          givenClientId, givenRoomIdExpected, givenReservationDateExpected);
      hotelUnderTests.addNewReservation(
          givenClientId, givenRoomIdUnexpected, givenReservationDateUnexpected);
    } catch (Exception exception) {
      Assertions.fail(
          "Exception thrown when adding a new reservation for the test setup", exception);
    }

    // when
    final Throwable receivedReservationOnSameRoomAndSameDateException =
        Assertions.catchThrowable(() -> {
          hotelUnderTests.addNewReservation(
              givenClientId, givenRoomIdExpected, givenReservationDateExpected);
        });
    final Throwable receivedReservationOnSameRoomAndDifferentDateException =
        Assertions.catchThrowable(() -> {
          hotelUnderTests.addNewReservation(
              givenClientId, givenRoomIdExpected, givenReservationDateUnexpected);
        });
    final Throwable receivedReservationOnDifferentRoomAndSameDateException =
        Assertions.catchThrowable(() -> {
          hotelUnderTests.addNewReservation(
              givenClientId, givenRoomIdUnexpected, givenReservationDateExpected);
        });

    // then
    Assertions.assertThat(receivedReservationOnSameRoomAndSameDateException)
        .isInstanceOf(RoomReservedException.class);
    Assertions.assertThat(receivedReservationOnSameRoomAndDifferentDateException).isEqualTo(null);
    Assertions.assertThat(receivedReservationOnDifferentRoomAndSameDateException).isEqualTo(null);
  }

  @Test
  public void reservationShouldBeConfirmedIfRequested() {
    final Hotel hotelUnderTests = new Hotel("Hotel under tests");
    final String givenClientId =
        hotelUnderTests.addClient("Given", "Client", LocalDate.now().minusYears(20));
    final String givenRoomId = hotelUnderTests.addRoom(1, 2, false, "Given room");
    final LocalDate givenReservationDate = LocalDate.now();
    final boolean givenReservationIsConfirmed = true;
    String givenReservationId = null;
    try {
      givenReservationId =
          hotelUnderTests.addNewReservation(givenClientId, givenRoomId, givenReservationDate);
    } catch (Exception exception) {
      Assertions.fail("Unexpected exception thrown", exception);
    }

    // when
    try {
      hotelUnderTests.confirmReservation(givenReservationId);
    } catch (Exception exception) {
      Assertions.fail("Unexpected exception thrown", exception);
    }

    RoomReservation receivedReservation = null;
    try {
      receivedReservation = hotelUnderTests.getReservation(givenReservationId);
    } catch (Exception exception) {
      Assertions.fail("Unexpected exception thrown", exception);
    }
    final boolean receivedReservationIsConfirmed = receivedReservation.isConfirmed();

    // then
    Assertions.assertThat(receivedReservationIsConfirmed).isEqualTo(givenReservationIsConfirmed);
  }

  @Test
  public void confirmingNonexistentReservationShouldThrowReservationNotFoundException() {
    // given
    final Hotel hotelUnderTests = new Hotel("Hotel under tests");
    final String givenNonexistentReservationId = "nonexistentReservationId";

    // when
    final Throwable receivedNonexistentReservationIdException = Assertions.catchThrowable(
        () -> { hotelUnderTests.confirmReservation(givenNonexistentReservationId); });

    // then
    Assertions.assertThat(receivedNonexistentReservationIdException)
        .isInstanceOf(ReservationNotFoundException.class);
  }

  @Test
  public void reservationShouldBeRemovable() {
    // given
    final Hotel hotelUnderTests = new Hotel("Hotel under tests");
    final String givenClientId =
        hotelUnderTests.addClient("Given", "Client", LocalDate.now().minusYears(20));
    final String givenRoomId = hotelUnderTests.addRoom(1, 2, false, "Given room");
    String tmpReservationId = null;
    try {
      tmpReservationId =
          hotelUnderTests.addNewReservation(givenClientId, givenRoomId, LocalDate.now());
    } catch (Exception exception) {
      Assertions.fail("Unexpected exception thrown", exception);
    }
    final String givenReservationId = tmpReservationId;

    // when
    final Throwable receivedRequestedReservationRemovalException =
        Assertions.catchThrowable(() -> { hotelUnderTests.removeReservation(givenReservationId); });
    final Throwable receivedRequestedRemovedReservationException =
        Assertions.catchThrowable(() -> { hotelUnderTests.getReservation(givenReservationId); });

    // then
    Assertions.assertThat(receivedRequestedReservationRemovalException).isEqualTo(null);
    Assertions.assertThat(receivedRequestedRemovedReservationException)
        .isInstanceOf(ReservationNotFoundException.class);
  }

  @Test
  public void removingNonexistentReservationShouldThrowReservationNotFoundException() {
    // given
    final Hotel hotelUnderTests = new Hotel("Hotel under tests");
    final String givenNonexistentReservationId = "nonexistentReservationId";

    // when
    final Throwable receivedRequestedNonexistentReservationRemovalException =
        Assertions.catchThrowable(
            () -> { hotelUnderTests.removeReservation(givenNonexistentReservationId); });

    // then
    Assertions.assertThat(receivedRequestedNonexistentReservationRemovalException)
        .isInstanceOf(ReservationNotFoundException.class);
  }

  @Test
  public void roomShouldBeReservedOnlyIfReservationOnDateAndRoomExists() {
    final Hotel hotelUnderTests = new Hotel("Hotel under tests");
    final String givenClientId =
        hotelUnderTests.addClient("Given", "Client", LocalDate.now().minusYears(20));
    final String givenRoomIdExpected = hotelUnderTests.addRoom(1, 2, false, "Expected");
    final String givenRoomIdUnexpected = hotelUnderTests.addRoom(1, 2, false, "Unexpected");
    final LocalDate givenReservationDateExpected = LocalDate.now();
    final LocalDate givenReservationDateUnexpected = givenReservationDateExpected.minusDays(1);
    // add a reservation for the expected date and the expected room (for date check verification)
    try {
      hotelUnderTests.addNewReservation(
          givenClientId, givenRoomIdExpected, givenReservationDateExpected);
    } catch (Exception exception) {
      Assertions.fail("Unexpected exception thrown", exception);
    }
    // add a reservation for the expected date and the unexpected room (for room check verification)
    try {
      hotelUnderTests.addNewReservation(
          givenClientId, givenRoomIdUnexpected, givenReservationDateExpected);
    } catch (Exception exception) {
      Assertions.fail("Unexpected exception thrown", exception);
    }
    boolean givenIsRoomReservedOnDateExpected = true;
    boolean givenIsRoomReservedOnDateUnexpected = false;

    // when
    boolean receivedIsRoomReservedOnDateExpected = !givenIsRoomReservedOnDateExpected;
    try {
      receivedIsRoomReservedOnDateExpected =
          hotelUnderTests.isRoomReserved(givenRoomIdExpected, givenReservationDateExpected);
    } catch (Exception exception) {
      Assertions.fail("Unexpected exception thrown", exception);
    }
    boolean receivedIsRoomReservedOnDateUnexpected = !givenIsRoomReservedOnDateUnexpected;
    try {
      receivedIsRoomReservedOnDateUnexpected =
          hotelUnderTests.isRoomReserved(givenRoomIdExpected, givenReservationDateUnexpected);
    } catch (Exception exception) {
      Assertions.fail("Unexpected exception thrown", exception);
    }

    // then
    Assertions.assertThat(receivedIsRoomReservedOnDateExpected)
        .isEqualTo(givenIsRoomReservedOnDateExpected);
    Assertions.assertThat(receivedIsRoomReservedOnDateUnexpected)
        .isEqualTo(givenIsRoomReservedOnDateUnexpected);
  }

  @Test
  public void checkingIfNonexistentRoomIsReservedShouldThrowRoomNotFoundException() {
    // given
    final Hotel hotelUnderTests = new Hotel("Hotel under tests");
    final String givenNonexistentRoomId = "nonexistentRoomId";

    // when
    final Throwable receivedNonexistentReservationIdException = Assertions.catchThrowable(
        () -> { hotelUnderTests.isRoomReserved(givenNonexistentRoomId, LocalDate.now()); });

    // then
    Assertions.assertThat(receivedNonexistentReservationIdException)
        .isInstanceOf(RoomNotFoundException.class);
  }

  @Test
  public void numberOfUnconfirmedReservationsOnDateShouldBeProperlyReturned() {
    // given
    final Hotel hotelUnderTests = new Hotel("Hotel under tests");
    final LocalDate givenExpectedUnconfirmedReservationDate = LocalDate.now();
    final LocalDate givenUnexpectedUnconfirmedReservationDate = LocalDate.now().minusDays(1);
    final int givenUnconfirmedReservationsCountOnExpectedDate = 2;
    final int givenUnconfirmedReservationsCountOnUnexpectedDate = 3;
    final int givenConfirmedReservationsCountOnExpectedDate = 5;
    final int givenTotalUnconfirmedReservationsCount =
        givenUnconfirmedReservationsCountOnExpectedDate
        + givenUnconfirmedReservationsCountOnUnexpectedDate;
    final int givenTotalReservationsCount =
        givenTotalUnconfirmedReservationsCount + givenConfirmedReservationsCountOnExpectedDate;

    // create given clients for the reservations
    final List<String> givenClientIds = new ArrayList<String>();
    for (int i = 0; i < givenTotalReservationsCount; i++) {
      givenClientIds.add(
          hotelUnderTests.addClient("Client", Integer.toString(i), LocalDate.now().minusYears(i)));
    }
    // create given rooms for the reservations
    final List<String> givenRoomIds = new ArrayList<String>();
    for (int i = 0; i < givenTotalReservationsCount; i++) {
      givenRoomIds.add(hotelUnderTests.addRoom(i, i, false, "Room"));
    }

    // create given reservations
    // keep a rolling count of created reservations outside loops' scope
    int receivedTotalReservationsCount = 0;
    // create unconfirmed reservations on the expected date (for confirmation check verification)
    for (int i = 0; i < givenUnconfirmedReservationsCountOnExpectedDate; i++) {
      final String clientId = givenClientIds.get(receivedTotalReservationsCount);
      final String roomId = givenRoomIds.get(receivedTotalReservationsCount);
      final LocalDate date = givenExpectedUnconfirmedReservationDate;
      try {
        hotelUnderTests.addNewReservation(clientId, roomId, date);
      } catch (Exception exception) {
        Assertions.fail("Unexpected exception thrown", exception);
      }
      receivedTotalReservationsCount++;
    }
    // sanity check due to the complexity of the test setup
    Assertions.assertThat(receivedTotalReservationsCount)
        .isEqualTo(givenUnconfirmedReservationsCountOnExpectedDate);
    // create unconfirmed reservations on the unexpected date (for date check verification)
    for (int i = 0; i < givenUnconfirmedReservationsCountOnUnexpectedDate; i++) {
      final String clientId = givenClientIds.get(receivedTotalReservationsCount);
      final String roomId = givenRoomIds.get(receivedTotalReservationsCount);
      final LocalDate date = givenUnexpectedUnconfirmedReservationDate;
      try {
        hotelUnderTests.addNewReservation(clientId, roomId, date);
      } catch (Exception exception) {
        Assertions.fail("Unexpected exception thrown", exception);
      }
      receivedTotalReservationsCount++;
    }
    // sanity check
    Assertions.assertThat(receivedTotalReservationsCount)
        .isEqualTo(givenTotalUnconfirmedReservationsCount);
    // create confirmed reservations on the expected date (for confirmation check verification)
    for (int i = 0; i < givenConfirmedReservationsCountOnExpectedDate; i++) {
      String roomReservationId = null;
      final String clientId = givenClientIds.get(receivedTotalReservationsCount);
      final String roomId = givenRoomIds.get(receivedTotalReservationsCount);
      // `[...]Unconfirmed[...]` - not a typo, the date has to be the same
      final LocalDate date = givenExpectedUnconfirmedReservationDate;
      try {
        roomReservationId = hotelUnderTests.addNewReservation(clientId, roomId, date);
      } catch (Exception exception) {
        Assertions.fail("Unexpected exception thrown", exception);
      }
      try {
        hotelUnderTests.confirmReservation(roomReservationId);
      } catch (Exception exception) {
        Assertions.fail("Unexpected exception thrown", exception);
      }
      receivedTotalReservationsCount++;
    }
    // sanity check
    Assertions.assertThat(receivedTotalReservationsCount).isEqualTo(givenTotalReservationsCount);

    // when
    int receivedUnconfirmedReservationsOnExpectedDate =
        hotelUnderTests.getNumberOfUnconfirmedReservation(givenExpectedUnconfirmedReservationDate);

    // then
    Assertions.assertThat(receivedUnconfirmedReservationsOnExpectedDate)
        .isEqualTo(givenUnconfirmedReservationsCountOnExpectedDate);
  }

  @Test
  public void roomIdsEverReservedByClientShouldBeReturnedProperly() {
    // given
    final Hotel hotelUnderTests = new Hotel("Hotel under tests");
    final String givenClientIdExpected =
        hotelUnderTests.addClient("Client", "Expected", LocalDate.now().minusYears(21));
    final String givenClientIdUnexpected =
        hotelUnderTests.addClient("Client", "Unexpected", LocalDate.now().minusYears(37));
    final LocalDate givenReservationDate = LocalDate.now();
    final int givenClientExpectedReservationsCount = 2;
    final int givenClientUnexpectedReservationsCount = 5;
    final int givenTotalClientReservationCount =
        givenClientExpectedReservationsCount + givenClientUnexpectedReservationsCount;

    // create given rooms for the reservations
    final List<String> givenRoomIds = new ArrayList<String>();
    for (int i = 0; i < givenTotalClientReservationCount; i++) {
      givenRoomIds.add(hotelUnderTests.addRoom(i, i, false, "Room"));
    }

    // create given reservations
    // keep a rolling count of created reservations outside loops' scope
    int receivedTotalReservationsCount = 0;
    // create reservations for the expected client (for client check validation)
    // keep the expected room IDs
    List<String> givenClientExpectedRoomIds = new ArrayList<String>();
    for (int i = 0; i < givenClientExpectedReservationsCount; i++) {
      final String roomId = givenRoomIds.get(receivedTotalReservationsCount);
      final LocalDate reservationDate =
          givenReservationDate.minusDays(receivedTotalReservationsCount);
      try {
        hotelUnderTests.addNewReservation(givenClientIdExpected, roomId, reservationDate);
      } catch (Exception exception) {
        Assertions.fail("Unexpected exception thrown", exception);
      }
      givenClientExpectedRoomIds.add(roomId);
      receivedTotalReservationsCount++;
    }
    // sanity check
    Assertions.assertThat(receivedTotalReservationsCount)
        .isEqualTo(givenClientExpectedReservationsCount);
    // create reservations for the unexpected client (for client check validation)
    for (int i = 0; i < givenClientUnexpectedReservationsCount; i++) {
      final String roomId = givenRoomIds.get(receivedTotalReservationsCount);
      final LocalDate reservationDate =
          givenReservationDate.minusDays(receivedTotalReservationsCount);
      try {
        hotelUnderTests.addNewReservation(givenClientIdUnexpected, roomId, reservationDate);
      } catch (Exception exception) {
        Assertions.fail("Unexpected exception thrown", exception);
      }
      receivedTotalReservationsCount++;
    }
    // sanity check
    Assertions.assertThat(receivedTotalReservationsCount)
        .isEqualTo(givenTotalClientReservationCount);

    // when
    try {
      Collection<String> receivedClientExpectedReservationIds =
          hotelUnderTests.getRoomIdsReservedByClient(givenClientIdExpected);

      // then
      Assertions.assertThat(receivedClientExpectedReservationIds)
          .containsOnlyElementsOf(givenClientExpectedRoomIds);
    } catch (Exception exception) {
      Assertions.fail("Unexpected exception thrown", exception);
    }
  }

  @Test
  public void checkingRoomIdsReservedByNonexistentClientShouldThrowClientNotFoundException() {
    // given
    final Hotel hotelUnderTests = new Hotel("Hotel under tests");
    final String givenNonexistentClientId = "nonexistentClientId";

    // when
    final Throwable receivedNonexistentReservationIdException = Assertions.catchThrowable(
        () -> { hotelUnderTests.getRoomIdsReservedByClient(givenNonexistentClientId); });

    // then
    Assertions.assertThat(receivedNonexistentReservationIdException)
        .isInstanceOf(ClientNotFoundException.class);
  }
}
