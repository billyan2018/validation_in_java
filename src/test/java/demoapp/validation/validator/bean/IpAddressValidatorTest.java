package demoapp.validation.validator.bean;

import demoapp.validation.validator.bean.IpAddressValidator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;


class IpAddressValidatorTest {

  private final IpAddressValidator validator = new IpAddressValidator();

  @ParameterizedTest
  @ValueSource(strings = {"111.111.111.111", "10.1.1.1"})
  void shouldReturnTrue_whenValid(String value) {
    assertTrue(check(value));
  }

  @ParameterizedTest
  @ValueSource(strings = {"111.111.abc.111", "10.1.1.256", "4.4.4"})
  void shouldReturnFalse_whenInvalid(String value) {
    assertFalse(check(value));
  }

  private boolean check(String value) {
    return validator.isValid(value, null);
  }
}