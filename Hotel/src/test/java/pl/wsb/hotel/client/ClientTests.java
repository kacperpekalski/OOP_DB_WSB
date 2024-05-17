package pl.wsb.hotel.client;

import java.time.LocalDate;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class ClientTests {
  @Test
  public void clientFullNameShouldProperlyConcatenated() {
    // given
    final String givenFirstName = "Given";
    final String givenLastName = "Client";
    final String givenFullName = String.format("%s %s", givenFirstName, givenLastName);
    final Client clientUnderTests = new Client("0", givenFirstName, givenLastName, LocalDate.now());

    // when
    final String receivedFullName = clientUnderTests.getFullName();

    // then
    Assertions.assertThat(receivedFullName).isEqualTo(givenFullName);
  }
}
