package com.robot_arm.demo.services.bluethoothService;

import com.fazecast.jSerialComm.SerialPort;
import org.springframework.stereotype.Service;

import javax.naming.OperationNotSupportedException;

@Service
public class SerialServiceImpl implements SerialService,SerialCommand {
    private SerialPort serialPort;


    public void sendCommand(String command) throws OperationNotSupportedException {
        if(isConnected()){
            byte[] bytes = command.getBytes() ;
            this.serialPort.writeBytes(bytes, bytes.length);
        }else {
            throw new OperationNotSupportedException("Not connected");
        }
    }

    @Override
    public void connect(String port) {
        if(isConnected()){
           this.serialPort.closePort();
        }

        this.serialPort = SerialPort.getCommPort(port);
        this.serialPort.setBaudRate(9600);

        try {
            this.serialPort.openPort();
        } catch (RuntimeException e) {
            throw new RuntimeException("Failed to open serial port " + port);
        }

    }

    @Override
    public void disconnectTheSerial() {
        if(isConnected()){
            this.serialPort.closePort();
        }
    }


    @Override
    public boolean isConnected() {
        return (this.serialPort != null && this.serialPort.isOpen());
    }

}
