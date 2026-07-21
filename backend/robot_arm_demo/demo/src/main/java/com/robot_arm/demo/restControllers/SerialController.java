package com.robot_arm.demo.restControllers;

import com.robot_arm.demo.services.serialService.SerialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/serial")
public class SerialController {
    private SerialService serialService;

    @Autowired
    public SerialController(SerialService serialService) {
        this.serialService = serialService;
    }

    @GetMapping("/isConnected")
    public ResponseEntity<Boolean>Connected() {
        boolean connected = serialService.isConnected();
        return  ResponseEntity.ok(connected);
    }

    @PostMapping("/connect")
    public ResponseEntity<Void> connect(@RequestParam("portName") String serialPort) {
        if (!serialService.isConnected()) {
            this.serialService.connect(serialPort);
           return ResponseEntity.ok().build();
        } else {
           return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/disconnect")
    public ResponseEntity<Void> disconnect() {
        if (!this.serialService.isConnected()) {
           return ResponseEntity.badRequest().build();
        }
        this.serialService.disconnectTheSerial();
        return ResponseEntity.ok().build();
    }
}
