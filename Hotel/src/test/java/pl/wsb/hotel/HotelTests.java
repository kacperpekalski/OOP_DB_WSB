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
}
