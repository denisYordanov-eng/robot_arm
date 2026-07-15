package com.robot_arm.demo.services.servoService.servoMotors;

import com.robot_arm.demo.enums.ServoMotorName;

public class Base extends ServoMotor {

    public Base(ServoMotorName servoMotorName, int angle) {
        super(servoMotorName, angle);
    }
    @Override
    public void setAngle(int angle) {
        if(angle < 0 || angle >= 180) {
            throw new IllegalArgumentException("angle out of bounds");
        }
        super.setAngle(angle);
    }
}
