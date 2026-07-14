package com.robot_arm.demo.services.serialService;

public interface SerialService {

    void connect(String port);

    void disconnectTheSerial();

    boolean isConnected();
}
