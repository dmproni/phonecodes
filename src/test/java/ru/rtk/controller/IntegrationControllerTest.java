package ru.rtk.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.rtk.service.IntegrationService;

import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = IntegrationController.class)
public class IntegrationControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private IntegrationService integrationService;

    @Test
    void test() throws Exception {
        Mockito.doNothing().when(integrationService).refresh();

        mockMvc.perform(get("/rest/refresh")).andExpect(status().isOk());

        Mockito.verify(integrationService, times(1)).refresh();
    }

}
