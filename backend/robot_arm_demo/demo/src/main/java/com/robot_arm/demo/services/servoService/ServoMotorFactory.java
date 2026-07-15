package com.robot_arm.demo.services.servoService;

import com.robot_arm.demo.enums.ServoMotorName;
import com.robot_arm.demo.services.servoService.servoMotors.*;
import org.springframework.stereotype.Component;

@Component
public class ServoMotorFactory {
    public ServoMotor getServoMotor(ServoMotorName servoMotorName, int angle) {

        switch (servoMotorName) {
            case BASE:
                return new Base(ServoMotorName.BASE, angle);
            case SHOULDER:
                return new Shoulder(ServoMotorName.SHOULDER, angle);
            case ELBOW:
                return new Elbow(ServoMotorName.ELBOW, angle);
            case WRIST_PITCH:
                return new WristPitch(ServoMotorName.WRIST_PITCH, angle);
            case WRIST_ROLL:
                return new WristRoll(ServoMotorName.WRIST_ROLL, angle);
            case GRIPPER:
                return new Gripper(ServoMotorName.GRIPPER, angle);
            default:
                throw new RuntimeException("ServoMotorName not found");
        }

    }

}
