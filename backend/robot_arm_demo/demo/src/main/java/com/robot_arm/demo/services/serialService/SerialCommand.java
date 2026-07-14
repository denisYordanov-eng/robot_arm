package com.robot_arm.demo.services.serialService;

import javax.naming.OperationNotSupportedException;

public interface SerialCommand {
    void sendCommand(String command) throws OperationNotSupportedException;
}
