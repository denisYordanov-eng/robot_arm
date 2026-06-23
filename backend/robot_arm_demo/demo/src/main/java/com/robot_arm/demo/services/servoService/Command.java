package com.robot_arm.demo.services.servoService;

public interface Command {
    void setAngle(int angle);

    String createCommand();
}
