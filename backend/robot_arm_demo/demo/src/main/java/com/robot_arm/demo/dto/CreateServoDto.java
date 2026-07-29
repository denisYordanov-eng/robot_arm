package com.robot_arm.demo.dto;

import com.robot_arm.demo.enums.ServoMotorName;
import jakarta.validation.constraints.NotNull;


public record CreateServoDto(
        @NotNull(message = "Servo motor name is required")
        ServoMotorName servoMotorName,

        @NotNull(message = "angle is required")
        int angle) {
}
