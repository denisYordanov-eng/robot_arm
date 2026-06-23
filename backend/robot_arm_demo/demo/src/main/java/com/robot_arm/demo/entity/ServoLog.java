package com.robot_arm.demo.entity;

import com.robot_arm.demo.enums.ServoMotorName;
import jakarta.persistence.*;


@Entity
@Table(name = "servos")
public class ServoLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(name = "servo_motor_type",nullable = false)
    private ServoMotorName servoMotorName;

    @Column(name = "angle")
    private int angle;

    public ServoLog() {
    }

    public ServoLog(ServoMotorName servoMotorName, int angle) {
        this.servoMotorName = servoMotorName;
        this.angle = angle;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ServoMotorName getServoMotorName() {
        return this.servoMotorName;
    }

    public void setServoMotorName(ServoMotorName servoMotorName) {
        this.servoMotorName = servoMotorName;
    }

    public int getAngle() {
        return this.angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }
}
