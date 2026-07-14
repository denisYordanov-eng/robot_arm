package com.robot_arm.demo.services.bluethoothService;

import javax.naming.OperationNotSupportedException;

public interface SerialCommand {
    void sendCommand(String command) throws OperationNotSupportedException;
}
