package demoapp.validation.controller.requestbody;

import com.fasterxml.jackson.databind.ObjectMapper;
import demoapp.validation.modal.DeviceInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ValidateRequestBodyWithSpringValidatorControllerTest {

  @Autowired
  private MockMvc mvc;

  @Autowired
  private ObjectMapper objectMapper;

  private static DeviceInfo prepareInvalidInput() {
    DeviceInfo input = new DeviceInfo();
    input.setIpAddress("invalid");
    input.setNumberBetweenOneAndTen(9);
    return input;
  }

  @Test
  void whenInputIsInvalid_thenReturnsStatus400WithErrorObject() throws Exception {
    DeviceInfo input = prepareInvalidInput();
    String body = objectMapper.writeValueAsString(input);

    MvcResult result = mvc.perform(post("/validateBody3")
            .contentType("application/json")
            .content(body))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(status().isBadRequest())
            .andReturn();

    assertEquals("invalid input", result.getResponse().getContentAsString());
  }

  @Test
  void whenInputIsValid_thenReturnsStatus200() throws Exception {
    DeviceInfo input = prepareValidInput();
    String body = objectMapper.writeValueAsString(input);

    mvc.perform(post("/validateBody3")
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