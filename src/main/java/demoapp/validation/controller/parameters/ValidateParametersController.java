package demoapp.validation.controller.parameters;

import javax.validation.constraints.Min;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;

import org.springframework.web.bind.annotation.*;

@RestController
@Validated
class ValidateParametersController {

  @GetMapping("/validatePathVariable/{id}")
  ResponseEntity<String> validatePathVariable(@PathVariable("id") @Min(5) int id) {
    return ResponseEntity.ok("valid");
  }

  @GetMapping("/validateRequestParameter")
  ResponseEntity<String> validateRequestParameter(@RequestParam("param") @Min(5) int param) {
    return ResponseEntity.ok("valid");
  }
}
