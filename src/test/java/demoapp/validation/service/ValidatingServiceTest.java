package demoapp.validation.service;

import javax.validation.ConstraintViolationException;

import demoapp.validation.modal.Device;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ValidatingServiceTest {

  @Autowired
  private ValidatingService service;

  @Test
  void whenInputIsValid_thenThrowsNoException(){
    Device device = new Device();
    device.setNumberBetweenOneAndTen(5);
    device.setIpAddress("111.111.111.111");

    service.validateInput(device);

    // then no exception
  }

  @Test
  void whenInputIsInvalid_thenThrowsException(){
    Device device = new Device();
    device.setNumberBetweenOneAndTen(0);
    device.setIpAddress("invalid");

    assertThrows(ConstraintViolationException.class, () -> {
      service.validateInput(device);
    });
  }

}