package com.robot_arm.demo.services.servoService;

import com.robot_arm.demo.enums.ServoMotorName;
import org.junit.Before;
import org.junit.Test;


import static org.junit.jupiter.api.Assertions.assertTrue;

public class ServoMotorFactoryTest {

    public static final ServoMotorName SERVO_NAME_GRIPPER = ServoMotorName.GRIPPER;
    public static final ServoMotorName SERVO_NAME_WRIST = ServoMotorName.WRIST;
    public static final ServoMotorName SERVO_NAME_SHOULDER = ServoMotorName.SHOULDER;
    public static final ServoMotorName SERVO_NAME_ELBOW = ServoMotorName.ELBOW;
    public static final int CORRECT_ANGLE = 20;
    public static final int INCORRECT_ANGLE = 400;
    public static final int NEGATIVE_ANGLE = -5;

    private ServoMotorFactory servoMotorFactory;

    @Before
    public void createServoMotorFactory() {
        servoMotorFactory = new ServoMotorFactory();
    }

    @Test
    public void getServoMotorFactoryGripperInstanceCorrectAngle() {
        ServoMotor motor = this.servoMotorFactory.getServoMotor(SERVO_NAME_GRIPPER, CORRECT_ANGLE);

        assertTrue(motor instanceof Gripper);
    }

    @Test(expected=IllegalArgumentException.class)
    public void getServoMotorFactoryGripperIncorrectAngle() {
        ServoMotor motor = this.servoMotorFactory.getServoMotor(SERVO_NAME_GRIPPER, INCORRECT_ANGLE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ServoMotorFactoryGripperNegativeAngle(){
        ServoMotor motor = this.servoMotorFactory.getServoMotor(SERVO_NAME_GRIPPER, NEGATIVE_ANGLE);
    }

    @Test
    public void getServoMotorFactoryWristInstanceCorrectAngle() {
        ServoMotor motor = this.servoMotorFactory.getServoMotor(SERVO_NAME_WRIST, CORRECT_ANGLE);
        assertTrue(motor instanceof Wrist);
    }

    @Test(expected=IllegalArgumentException.class)
    public void getServoMotorFactoryWristIncorrectAngle() {
        ServoMotor motor = this.servoMotorFactory.getServoMotor(SERVO_NAME_WRIST, INCORRECT_ANGLE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ServoMotorFactoryWristNegativeAngle(){
        ServoMotor motor = this.servoMotorFactory.getServoMotor(SERVO_NAME_WRIST, NEGATIVE_ANGLE);
    }

    @Test
    public void getServoMotorFactoryShoulderInstanceCorrectAngle() {
        ServoMotor motor = this.servoMotorFactory.getServoMotor(SERVO_NAME_SHOULDER, CORRECT_ANGLE);
        assertTrue(motor instanceof Shoulder);
    }
    @Test(expected= IllegalArgumentException.class)
    public void getServoMotorFactoryShoulderIncorrectAngle() {
        ServoMotor motor = this.servoMotorFactory.getServoMotor(SERVO_NAME_SHOULDER, INCORRECT_ANGLE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ServoMotorFactoryShoulderNegativeAngle(){
        ServoMotor motor = this.servoMotorFactory.getServoMotor(SERVO_NAME_SHOULDER, NEGATIVE_ANGLE);
    }

    @Test
    public void getServoMotorFactoryElbowInstanceCorrectAngle() {
        ServoMotor motor = this.servoMotorFactory.getServoMotor(SERVO_NAME_ELBOW, CORRECT_ANGLE);
        assertTrue(motor instanceof Elbow);
    }

    @Test(expected=IllegalArgumentException.class)
    public void getServoMotorFactoryElbowIncorrectAngle() {
        ServoMotor motor = this.servoMotorFactory.getServoMotor(SERVO_NAME_ELBOW, INCORRECT_ANGLE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ServoMotorFactoryElbowNegativeAngle(){
        ServoMotor motor = this.servoMotorFactory.getServoMotor(ServoMotorName.ELBOW, NEGATIVE_ANGLE);
    }
}