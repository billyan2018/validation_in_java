package demoapp.validation.validator.spring;

import demoapp.validation.modal.Device;
import demoapp.validation.modal.DeviceInfo;
import org.junit.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class DeviceInfoValidatorTest {

    private final DeviceInfoValidator validator = new DeviceInfoValidator();

    @Test
    public void shouldSupport_whenClassIsDevice() {
        assertTrue(validator.supports(DeviceInfo.class));
    }

    @Test
    public void shouldNotSupport_whenClassIsNotDevice() {
        assertFalse(validator.supports(Device.class));
    }

    @Test
    public void shouldFail_whenDeviceInvalid() {
        DeviceInfo device = prepareInvalidInput();
        Errors errors = new BeanPropertyBindingResult(device, "validAddress");
        validator.validate(device, errors);
        assertEquals("Invalid IP address", errors.getFieldError("ipAddress").getCode());

    }

    void readFile1(String filename) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(filename),
                StandardCharsets.UTF_8);
        lines.forEach(System.out::println);
    }

    void readFile2(String filename) throws IOException {
        Stream<String> lines = Files.lines(Paths.get(filename));
        lines.forEach(System.out::println);
    }

    void readFile3(String filename) throws IOException {
        String content = Files.readString(Paths.get(filename));
        System.out.println(content);

    }

    void readFile11(String filename) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        }
    }

    void readFile15(String filename) throws FileNotFoundException {
        try (Scanner sc = new Scanner(new FileReader(filename))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
            }
        }
    }



    private static DeviceInfo prepareInvalidInput() {
        DeviceInfo input = new DeviceInfo();
        input.setIpAddress("invalid");
        input.setNumberBetweenOneAndTen(99);
        return input;
    }
}
