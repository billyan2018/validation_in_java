package demoapp.validation.controller.requestbody;

import com.fasterxml.jackson.databind.ObjectMapper;
import demoapp.validation.modal.DeviceInfo;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = ValidateRequestBodyWithCustomValidatorController.class)
class ValidateRequestBodyWithCustomValidatorControllerTest {

  @Autowired
  private MockMvc mvc;

  @Autowired
  private ObjectMapper objectMapper;

  private static DeviceInfo prepareInvalidInput() {
    DeviceInfo input = new DeviceInfo();
    input.setIpAddress("257.257.257.257");
    input.setNumberBetweenOneAndTen(9);
    return input;
  }

  @Test
  void whenInputIsInvalid_thenReturnsStatus400WithErrorObject() throws Exception {
    DeviceInfo input = prepareInvalidInput();
    String body = objectMapper.writeValueAsString(input);

    mvc.perform(post("/validateBody2")
            .contentType("application/json")
            .content(body))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$[*].fieldName", contains("ipAddress")));

  }

  @Test
  void whenInputIsValid_thenReturnsStatus200() throws Exception {
    DeviceInfo input = prepareValidInput();
    String body = objectMapper.writeValueAsString(input);

    mvc.perform(post("/validateBody2")
            .contentType("application/json")
            .content(body))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(status().isOk());
  }

  private static DeviceInfo prepareValidInput() {
    DeviceInfo input = new DeviceInfo();
    input.setIpAddress("255.255.255.255");
    input.setNumberBetweenOneAndTen(10);
    return input;
  }

}
