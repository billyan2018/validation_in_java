package demoapp.validation.controller.requestbody;

import demoapp.validation.modal.DeviceInfo;
import demoapp.validation.validator.spring.DeviceInfoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
class ValidateRequestBodyWithSpringValidatorController {


  private final DeviceInfoValidator deviceInfoValidator;

  ValidateRequestBodyWithSpringValidatorController(DeviceInfoValidator validator) {
    this.deviceInfoValidator = validator;
  }

  @PostMapping("/validateBody3")
  ResponseEntity<String> validateBodyWithCustomValidator(@RequestBody DeviceInfo input, Errors errors
  ) {
    deviceInfoValidator.validate(input, errors);
    if (errors.getErrorCount() > 0) {
      return ResponseEntity.badRequest().body("invalid input");
    }
    return ResponseEntity.ok("valid");
  }
}
