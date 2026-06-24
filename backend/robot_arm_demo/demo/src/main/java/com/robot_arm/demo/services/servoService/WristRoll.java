package com.robot_arm.demo.services.servoService;

import com.robot_arm.demo.enums.ServoMotorName;

public class WristRoll extends ServoMotor{
    public WristRoll(ServoMotorName servoMotorName, int angle) {
        super(servoMotorName, angle);
    }

    @Override
    public void setAngle(int angle) {
        if(angle < 0 || angle >= 60) {
            throw new IllegalArgumentException("angle out of bounds");
        }
    }
}
