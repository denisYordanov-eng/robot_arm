package com.robot_arm.demo.restControllers;

import com.robot_arm.demo.services.serialService.SerialService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SerialController.class)
class SerialControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private SerialService serialService;


    @Test
    void connected() throws Exception {
        Mockito.when(serialService.isConnected()).thenReturn(true);
        mockMvc.perform(get("/serial/isConnected"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

    }

    @Test
    void isNotConnected() throws Exception {
        Mockito.when(serialService.isConnected()).thenReturn(false);

        mockMvc.perform(get("/serial/isConnected"))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
    }

    @Test
    void connect() throws Exception {
        Mockito.when(serialService.isConnected()).thenReturn(false);

        mockMvc.perform(post("/serial/connect").param("portName", "COM4"))
                .andExpect(status().isOk());

        Mockito.verify(serialService, Mockito.times(1)).connect("COM4");
    }

    @Test
    void cannotConnect() throws Exception {
        Mockito.when(serialService.isConnected()).thenReturn(true);

        mockMvc.perform(post("/serial/connect").param("portName", "COM4"))
                .andExpect(status().isBadRequest());
        Mockito.verify(serialService, Mockito.never()).connect(Mockito.anyString());
    }


    @Test
    void disconnect() throws Exception {
        Mockito.when(serialService.isConnected()).thenReturn(true);

        mockMvc.perform(post("/serial/disconnect"))
                .andExpect(status().isOk());
    }

    @Test
    void disconnectShouldReturnBadRequest() throws Exception {
        Mockito.when(serialService.isConnected()).thenReturn(false);

        mockMvc.perform(post("/serial/disconnect"))
                .andExpect(status().isBadRequest());
    }
}