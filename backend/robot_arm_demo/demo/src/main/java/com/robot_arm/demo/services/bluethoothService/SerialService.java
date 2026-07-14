package com.robot_arm.demo.services.bluethoothService;

public interface SerialService {

    void connect(String port);

    void disconnectTheSerial();

    boolean isConnected();
}
