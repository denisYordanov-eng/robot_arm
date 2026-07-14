package com.robot_arm.demo.restControllers;

import com.robot_arm.demo.services.serialService.SerialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class SerialController {
    private SerialService serialService;

    @Autowired
    public SerialController(SerialService serialService) {
        this.serialService = serialService;
    }

    @GetMapping("/serialIsConnected")
    public String isConnected() {
        if (serialService.isConnected()) {
            return "This serial is connected";
        } else {
            return "This serial is not connected";
        }
    }

    @PostMapping("/serialConnect")
    public String connect(@RequestParam("portName") String serialPort) {
        if (!serialService.isConnected()) {
            this.serialService.connect(serialPort);
            return "This serial is connected";
        } else {
            throw new RuntimeException("This serial is already connected");
        }
    }

    @PostMapping("/serialDisconnect")
    public String disconnect() {
        if (!this.serialService.isConnected()) {
            throw new RuntimeException("This serial is already disconnected");
        }
        this.serialService.disconnectTheSerial();
        return "This serial is disconnected";
    }
}
