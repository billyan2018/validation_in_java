package demoapp.validation.validator.bean;

import javax.validation.ConstraintViolationException;

import demoapp.validation.modal.Device;
import demoapp.validation.service.ProgrammaticallyValidatingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ProgrammaticallyValidatingServiceTest {

  @Autowired
  private ProgrammaticallyValidatingService service;

  @Test
  void whenInputIsInvalid_thenThrowsException(){
    Device device = new Device();
    device.setNumberBetweenOneAndTen(0);
    device.setIpAddress("invalid");

    assertThrows(ConstraintViolationException.class, () -> {
      service.validateInput(device);
    });
  }

  @Test
  void givenInjectedValidator_whenInputIsInvalid_thenThrowsException(){
    Device device = new Device();
    device.setNumberBetweenOneAndTen(0);
    device.setIpAddress("invalid");

    assertThrows(ConstraintViolationException.class, () -> {
      service.validateInputWithInjectedValidator(device);
    });
  }

}