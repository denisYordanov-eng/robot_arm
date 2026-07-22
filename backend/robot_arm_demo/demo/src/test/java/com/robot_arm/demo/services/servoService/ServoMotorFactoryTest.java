package com.robot_arm.demo.services.servoService;

import com.robot_arm.demo.enums.ServoMotorName;
import com.robot_arm.demo.services.servoService.servoMotors.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ServoMotorFactoryTest {

    public static final ServoMotorName SERVO_NAME_GRIPPER = ServoMotorName.GRIPPER;
    public static final ServoMotorName SERVO_NAME_WRIST_PITCH = ServoMotorName.WRIST_PITCH;
    public static final ServoMotorName SERVO_NAME_WRIST_ROLL = ServoMotorName.WRIST_ROLL;
    public static final ServoMotorName SERVO_NAME_ELBOW = ServoMotorName.ELBOW;
    public static final ServoMotorName SERVO_NAME_SHOULDER = ServoMotorName.SHOULDER;
    public static final ServoMotorName SERVO_NAME_BASE = ServoMotorName.BASE;
    public static final int CORRECT_ANGLE = 20;
    public static final int INCORRECT_ANGLE = 400;
    public static final int NEGATIVE_ANGLE = -5;

    private ServoMotorFactory servoMotorFactory;

    @BeforeEach
    public void createServoMotorFactory() {
        servoMotorFactory = new ServoMotorFactory();
    }

    @Test
    public void getServoMotorFactoryGripperInstanceCorrectAngle() {
        ServoMotor motor = this.servoMotorFactory.getServoMotor(SERVO_NAME_GRIPPER, CORRECT_ANGLE);
        assertTrue(motor instanceof Gripper);
    }

    @Test
    public void getServoMotorFactoryGripperIncorrectAngle() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.servoMotorFactory.getServoMotor(SERVO_NAME_GRIPPER, INCORRECT_ANGLE);
        });
    }

    @Test
    public void ServoMotorFactoryGripperNegativeAngle() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.servoMotorFactory.getServoMotor(SERVO_NAME_GRIPPER, NEGATIVE_ANGLE);
        });
    }

    @Test
    public void getServoMotorFactoryWristInstanceCorrectAngle() {
        ServoMotor motor = this.servoMotorFactory.getServoMotor(SERVO_NAME_WRIST_PITCH, CORRECT_ANGLE);
        assertTrue(motor instanceof WristPitch);
    }

    @Test
    public void getServoMotorFactoryWristIncorrectAngle() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.servoMotorFactory.getServoMotor(SERVO_NAME_WRIST_PITCH, INCORRECT_ANGLE);
        });
    }

    @Test
    public void ServoMotorFactoryWristNegativeAngle() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.servoMotorFactory.getServoMotor(SERVO_NAME_WRIST_PITCH, NEGATIVE_ANGLE);
        });
    }


    @Test
    public void getServoMotorFactoryShoulderInstanceCorrectAngle() {
        ServoMotor motor = this.servoMotorFactory.getServoMotor(SERVO_NAME_SHOULDER, CORRECT_ANGLE);
        assertTrue(motor instanceof Shoulder);
    }

    @Test
    public void getServoMotorFactoryShoulderIncorrectAngle() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.servoMotorFactory.getServoMotor(SERVO_NAME_SHOULDER, INCORRECT_ANGLE);
        });
    }

    @Test
    public void ServoMotorFactoryShoulderNegativeAngle() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.servoMotorFactory.getServoMotor(SERVO_NAME_SHOULDER, NEGATIVE_ANGLE);
        });
    }

    @Test
    public void getServoMotorFactoryElbowInstanceCorrectAngle() {
        ServoMotor motor = this.servoMotorFactory.getServoMotor(SERVO_NAME_ELBOW, CORRECT_ANGLE);
        assertTrue(motor instanceof Elbow);
    }

    @Test
    public void getServoFactoryElbowInstanceCorrectAngle() {
        ServoMotor motor = this.servoMotorFactory.getServoMotor(ServoMotorName.ELBOW, CORRECT_ANGLE);
        assertTrue(motor instanceof Elbow);
    }

    @Test
    public void getServoMotorFactoryElbowIncorrectAngle() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.servoMotorFactory.getServoMotor(SERVO_NAME_ELBOW, INCORRECT_ANGLE);
        });
    }

    @Test
    public void ServoMotorFactoryElbowNegativeAngle() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.servoMotorFactory.getServoMotor(ServoMotorName.ELBOW, NEGATIVE_ANGLE);
        });
    }


    @Test
    public void getServoMotorFactoryBaseInstanceCorrectAngle() {
        ServoMotor motor = this.servoMotorFactory.getServoMotor(SERVO_NAME_BASE, CORRECT_ANGLE);
        assertTrue(motor instanceof Base);
    }

    @Test
    public void getServoMotorFactoryBaseIncorrectAngle() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.servoMotorFactory.getServoMotor(SERVO_NAME_BASE, INCORRECT_ANGLE);
        });
    }

    @Test
    public void ServoMotorFactoryBaseNegativeAngle() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.servoMotorFactory.getServoMotor(SERVO_NAME_BASE, NEGATIVE_ANGLE);
        });
    }


    @Test
    public void getServoMotorFactoryWristRollInstanceCorrectAngle() {
        ServoMotor motor = this.servoMotorFactory.getServoMotor(SERVO_NAME_WRIST_ROLL, CORRECT_ANGLE);
        assertTrue(motor instanceof WristRoll);
    }

    @Test
    public void getServoMotorFactoryWristRollIncorrectAngle() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.servoMotorFactory.getServoMotor(SERVO_NAME_WRIST_ROLL, INCORRECT_ANGLE);
        });
    }

    @Test
    public void ServoMotorFactoryWristRollNegativeAngle() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.servoMotorFactory.getServoMotor(SERVO_NAME_WRIST_ROLL, NEGATIVE_ANGLE);
        });
    }
}