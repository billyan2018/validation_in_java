package demoapp.validation.service;

import javax.validation.Valid;

import demoapp.validation.modal.DeviceInfo;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
class ValidatingServiceWithGroups {

    @Validated(OnCreate.class)
    void validateForCreate(@Valid DeviceInfo input){
      // do something
    }

    @Validated(OnUpdate.class)
    void validateForUpdate(@Valid DeviceInfo input){
        // do something
    }

}
