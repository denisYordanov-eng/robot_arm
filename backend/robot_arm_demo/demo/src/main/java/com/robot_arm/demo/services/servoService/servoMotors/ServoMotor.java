package com.robot_arm.demo.services.servoService.servoMotors;

import com.robot_arm.demo.enums.ServoMotorName;

public abstract class ServoMotor  {
    private ServoMotorName servoMotorName;
    private int angle;

    protected ServoMotor(ServoMotorName servoMotorName, int angle) {
        this.servoMotorName = servoMotorName;
        setAngle(angle);
    }

    public ServoMotorName getServoMotorName() {
        return this.servoMotorName;
    }

    public int getAngle() {
        return this.angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }

    @Override
    public String toString() {
        return String.format("%s:%s\n",this.getServoMotorName(), this.angle);
    }
}
