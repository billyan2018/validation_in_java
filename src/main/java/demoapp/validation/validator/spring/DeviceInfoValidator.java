package demoapp.validation.validator.spring;

import demoapp.validation.modal.DeviceInfo;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class DeviceInfoValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return DeviceInfo.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        DeviceInfo device = (DeviceInfo) target;
        if (!device.getIpAddress().contains(".")) {
            errors.rejectValue("ipAddress","Invalid IP address");
        }
    }
}
