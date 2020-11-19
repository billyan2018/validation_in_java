package demoapp.validation.controller.requestbody;

import com.fasterxml.jackson.databind.ObjectMapper;
import demoapp.validation.modal.Device;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = ValidateRequestBodyController.class)
class ValidateRequestBodyControllerTest {

  @Autowired
  private MockMvc mvc;

  @Autowired
  private ObjectMapper objectMapper;

  private static Device prepareInvalidInput() {
    Device device = new Device();
    device.setIpAddress("invalid");
    device.setNumberBetweenOneAndTen(9);
    return device;
  }

  @Test
  void whenInputIsInvalid_thenReturnsStatus400WithErrorObject() throws Exception {
    Device device = prepareInvalidInput();
    String body = objectMapper.writeValueAsString(device);

    mvc.perform(post("/validateBody")
            .contentType("application/json")
            .content(body))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$[0].fieldName", is("ipAddress")))
            .andDo(MockMvcResultHandlers.print());

  }

  @Test
  void whenInputIsValid_thenReturnsStatus200() throws Exception {
    Device device = prepareValidInput();
    String body = objectMapper.writeValueAsString(device);

    mvc.perform(post("/validateBody")
            .contentType("application/json")
            .content(body))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(status().isOk());
  }

  private static Device prepareValidInput() {
    Device device = new Device();
    device.setIpAddress("855.255.255.256");
    device.setNumberBetweenOneAndTen(6);
    return device;
  }

}