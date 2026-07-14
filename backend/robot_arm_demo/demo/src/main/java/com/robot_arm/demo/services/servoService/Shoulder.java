package com.robot_arm.demo.services.servoService;

import com.robot_arm.demo.enums.ServoMotorName;


public class Shoulder extends ServoMotor {

    public Shoulder(ServoMotorName servoMotorName, int angle) {
        super(servoMotorName, angle);
    }


    @Override
    public void setAngle(int angle) {
        if(angle < 0 || angle > 180){
            throw new IllegalArgumentException("angle out of range");
        }
        super.setAngle(angle);
    }

@Override
    public String toString(){
        return String.format("%s:%s\n",ServoMotorName.WRIST_ROLL, getAngle() );
}

}
