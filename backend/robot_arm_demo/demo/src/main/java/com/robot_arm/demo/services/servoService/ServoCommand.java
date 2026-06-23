package com.robot_arm.demo.services.servoService;

import com.robot_arm.demo.enums.ServoMotorName;

public interface ServoCommand<T extends ServoMotor>{
    ServoMotor createCommand(ServoMotorName servoName,int angle);
}
