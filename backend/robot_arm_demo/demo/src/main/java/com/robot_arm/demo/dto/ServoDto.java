package com.robot_arm.demo.dto;

import com.robot_arm.demo.enums.ServoMotorName;

public record ServoDto(
        int id,
        ServoMotorName servoMotorName,
        int angle) {
}
