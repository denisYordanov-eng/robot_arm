package com.robot_arm.demo.services.servoService.servoMotors;

import com.robot_arm.demo.enums.ServoMotorName;

public class Gripper extends ServoMotor {


    public  Gripper(ServoMotorName servoMotorName, int angle) {
        super(servoMotorName, angle);
    }

    @Override
    public void setAngle(int angle) {
        if(angle < 0 || angle >= 60){
            throw new IllegalArgumentException("angle must be between 0 and 60");
        }
        super.setAngle(angle);
    }
}
