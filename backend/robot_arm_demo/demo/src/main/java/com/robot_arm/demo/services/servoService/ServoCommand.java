package com.robot_arm.demo.services.servoService;

import com.robot_arm.demo.enums.ServoMotorName;
import com.robot_arm.demo.services.servoService.servoMotors.ServoMotor;

public interface ServoCommand<T extends ServoMotor>{
    ServoMotor createServo(ServoMotorName servoName, int angle);
}
