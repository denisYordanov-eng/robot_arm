package com.robot_arm.demo.services.servoService;

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
@Override
    public String toString(){
        return String.format("%s:%s\n",ServoMotorName.GRIPPER, getAngle() );
}
}
