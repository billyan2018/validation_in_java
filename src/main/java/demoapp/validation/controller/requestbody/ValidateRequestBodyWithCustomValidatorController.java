package demoapp.validation.controller.requestbody;

import demoapp.validation.modal.DeviceInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
class ValidateRequestBodyWithCustomValidatorController {

  @PostMapping("/validateBody2")
  ResponseEntity<String> validateBodyWithCustomValidator(@Valid @RequestBody DeviceInfo input) {
    return ResponseEntity.ok("valid");
  }
}
