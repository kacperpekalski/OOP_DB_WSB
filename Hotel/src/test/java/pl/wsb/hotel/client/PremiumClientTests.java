package pl.wsb.hotel.client;

import java.time.LocalDate;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class PremiumClientTests {
  @Test
  public void clientPremiumFullNameShouldProperlyConcatenatedAndPrefixed() {
    // given
    final String givenPremiumPrefix = "[premium]";
    final String givenFirstName = "Given";
    final String givenLastName = "Client";
    final String givenFullName =
        String.format("%s %s %s", givenPremiumPrefix, givenFirstName, givenLastName);
    final Client clientPremiumUnderTests = new PremiumClient(
        "0", givenFirstName, givenLastName, LocalDate.now(), PremiumClientType.PREMIUM);
    final Client clientPremiumPlusUnderTests = new PremiumClient(
        "1", givenFirstName, givenLastName, LocalDate.now(), PremiumClientType.PREMIUM_PLUS);

    // when
    final String receivedPremiumFullName = clientPremiumUnderTests.getFullName();
    final String receivedPremiumPlusFullName = clientPremiumPlusUnderTests.getFullName();

    // then
    Assertions.assertThat(receivedPremiumFullName).isEqualTo(givenFullName);
    Assertions.assertThat(receivedPremiumPlusFullName).isEqualTo(givenFullName);
  }
}
