package demoapp.validation.service;

import javax.validation.Valid;

import demoapp.validation.modal.Device;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
class ValidatingService {

    void validateInput(@Valid Device device){
      // do something
    }

}
