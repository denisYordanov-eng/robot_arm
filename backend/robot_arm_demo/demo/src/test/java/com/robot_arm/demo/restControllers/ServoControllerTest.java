package com.robot_arm.demo.restControllers;

import com.robot_arm.demo.dto.CreateServoDto;
import com.robot_arm.demo.dto.ServoDto;
import com.robot_arm.demo.entity.ServoLog;
import com.robot_arm.demo.enums.ServoMotorName;
import com.robot_arm.demo.mapper.CreateServoMapper;
import com.robot_arm.demo.mapper.ServoMapper;
import com.robot_arm.demo.services.serialService.SerialCommand;
import com.robot_arm.demo.services.servoService.ServoCommand;
import com.robot_arm.demo.services.servoService.ServoLogService;
import com.robot_arm.demo.services.servoService.ServoServiceImpl;
import com.robot_arm.demo.services.servoService.servoMotors.ServoMotor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ServoController.class)
class ServoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ServoLogService<ServoLog> servoService;

    @MockitoBean
    private ServoCommand<ServoMotor> servoCommand;

    @MockitoBean
    private SerialCommand serialCommand;

    @MockitoBean
    private ServoMapper servoMapper;

    @MockitoBean
    private CreateServoMapper createServoMapper;

    @Test
    void findAll() throws Exception {
        mockMvc.perform(get("/api/servo"))
                .andExpect(status().isOk());
    }

    @Test
    void getServoLogServoLogPresent() throws Exception {
        ServoLog servoLog = new ServoLog();
        servoLog.setId(1);
        servoLog.setServoMotorName(ServoMotorName.ELBOW);
        servoLog.setAngle(20);

        ServoDto servoDto =  new ServoDto(1,ServoMotorName.ELBOW,20);

        when(servoService.findById(1)).thenReturn(servoLog);
        when(this.servoMapper.toDto(servoLog)).thenReturn(servoDto);

        mockMvc.perform(get("/api/servo/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void getServoLogServoLogNotPresent() throws Exception {
        ServoLog servoLog = null;

        when(this.servoService.findById(1)).thenReturn(servoLog);

        mockMvc.perform(get("/api/servo/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void createServo() throws Exception {
        ServoLog servoLog = new ServoLog(ServoMotorName.BASE,40);
        ServoMotor mockMotor = mock(ServoMotor.class);
        ServoDto mockServo = new ServoDto(1,ServoMotorName.BASE,40);

        when(createServoMapper.toEntity(any(CreateServoDto.class))).thenReturn(servoLog);
        when(servoCommand.createServo(eq(ServoMotorName.BASE),eq(40))).thenReturn(mockMotor);
        when(servoMapper.toDto(any(ServoLog.class))).thenReturn(mockServo);

        String jsonPayload = "{\"servoMotorName\":\"BASE\",\"angle\":40}";

        mockMvc.perform(post("/api/servo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonPayload))
                .andExpect(status().isCreated());
    }

    @Test
    void createServoNullServoMotor() throws Exception {
        ServoLog servoLog = new ServoLog(null,40);


        String jsonPayload = "{\"servoMotorName\":null,\"angle\":40}";
        mockMvc.perform(post("/api/servo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonPayload)).andExpect(status().isBadRequest());
    }

    @Test
    void patchServoLog() throws Exception {
        ServoLog servoLog = new ServoLog();

        when(servoService.findById(1)).thenReturn(servoLog);

        String jsonPayload = "{\"angle\":30}";

        mockMvc.perform(patch("/api/servo/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonPayload))
                .andExpect(status().isOk());
    }

    @Test
    void patchNullServoLog() throws Exception {
        when(servoService.findById(1)).thenReturn(null);
        String jsonPayload = "{\"angle\":30}";

        mockMvc.perform(patch("/api/servo/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonPayload))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteServoLog() throws Exception {
        ServoLog servoLog = new ServoLog();
        when(servoService.findById(1)).thenReturn(servoLog);

        mockMvc.perform(delete("/api/servo/1")).andExpect(status().isNoContent());
    }

    @Test
    void deleteNullServoLog() throws Exception {
        when(servoService.findById(1)).thenReturn(null);
        mockMvc.perform(delete("/api/servo/1")).andExpect(status().isNotFound());
    }
}
