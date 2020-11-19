package demoapp.validation.controller.requestbody;

import javax.validation.Valid;

import demoapp.validation.modal.Device;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

class ValidateRequestBodyController {

  @PostMapping("/validateBody")
  ResponseEntity<Device> validateBody(@Valid @RequestBody Device device) {
    return ResponseEntity.ok(device);
  }

}
