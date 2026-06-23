package com.robot_arm.demo.services.servoService;

import com.robot_arm.demo.enums.ServoMotorName;


public class Wrist extends ServoMotor {


    public Wrist(ServoMotorName servoMotorName, int angle) {

        super(servoMotorName, angle);
    }


    @Override
    public void setAngle(int angle) {
        if (angle < 0 || angle >= 60) {
            throw new IllegalArgumentException("angle must be greater than 0");
        }
       super.setAngle(angle);
    }
}
