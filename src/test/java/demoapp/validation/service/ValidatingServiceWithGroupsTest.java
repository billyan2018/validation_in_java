package demoapp.validation.service;

import javax.validation.ConstraintViolationException;

import demoapp.validation.modal.DeviceInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ValidatingServiceWithGroupsTest {

  @Autowired
  private ValidatingServiceWithGroups service;

  @Test
  void whenInputIsInvalidForCreate_thenThrowsException() {
    DeviceInfo input = validInput();
    input.setId(42L);
    assertThrows(ConstraintViolationException.class, () -> {
      service.validateForCreate(input);
    });
  }

  private DeviceInfo validInput() {
    DeviceInfo input = new DeviceInfo();
    input.setNumberBetweenOneAndTen(1);
    input.setIpAddress("111.111.111.111");
    return input;
  }

  @Test
  void whenInputIsInvalidForUpdate_thenThrowsException() {
    DeviceInfo input = validInput();
    input.setId(null);
    assertThrows(ConstraintViolationException.class, () -> {
      service.validateForUpdate(input);
    });
  }

}