package pl.wsb.hotel;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class SanityTest {
  @Test
  public void MathsShouldMath() {
    // given
    final int a = 1;
    final int b = 2;

    // when
    final int c = a + b;

    // then
    Assertions.assertThat(c).isEqualTo(3);
  }
}
