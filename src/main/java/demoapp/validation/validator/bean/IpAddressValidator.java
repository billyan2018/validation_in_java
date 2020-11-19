package demoapp.validation.validator.bean;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IpAddressValidator implements ConstraintValidator<IpAddress, String> {
  private static final Pattern IP_PATTERN = Pattern.compile("^([0-9]{1,3})\\.([0-9]{1,3})\\.([0-9]{1,3})\\.([0-9]{1,3})$");
  private static final int SECTIONS_COUNT = 4;

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    Matcher matcher = IP_PATTERN.matcher(value);
      if (!matcher.matches()) {
        return false;
      } else {
        for (int i = 1; i <= SECTIONS_COUNT; i++) {
          int octet = Integer.parseInt(matcher.group(i));
          if (octet > 255) {
            return false;
          }
        }
        return true;
      }
    }
}
