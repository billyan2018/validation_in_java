package demoapp.validation.service;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import demoapp.validation.modal.Device;
import org.springframework.stereotype.Service;

@Service
public class ProgrammaticallyValidatingService {

  private Validator validator;

  public ProgrammaticallyValidatingService(Validator validator) {
    this.validator = validator;
  }

  public void validateInput(Device device) {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();
    Set<ConstraintViolation<Device>> violations = validator.validate(device);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
  }

  public void validateInputWithInjectedValidator(Device device) {
    Set<ConstraintViolation<Device>> violations = validator.validate(device);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
  }
}
