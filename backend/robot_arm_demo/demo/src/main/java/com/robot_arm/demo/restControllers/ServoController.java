package com.robot_arm.demo.restControllers;


import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.robot_arm.demo.entity.ServoLog;
import com.robot_arm.demo.services.serialService.SerialCommand;
import com.robot_arm.demo.services.servoService.ServoCommand;
import com.robot_arm.demo.services.servoService.ServoLogService;
import com.robot_arm.demo.services.servoService.servoMotors.ServoMotor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.OperationNotSupportedException;
import java.util.List;
import java.util.Map;

import static com.robot_arm.demo.jsonMapperSingleton.JsonMapperSingleton.jsonMapperSingleton;

@RestController
@RequestMapping("/api/servo")
public class ServoController {
    private ServoLogService<ServoLog> servoService;
    private ServoCommand<ServoMotor> servoCommand;
    private SerialCommand serialCommand;

    @Autowired
    public ServoController(ServoLogService<ServoLog> servoService
            , ServoCommand<ServoMotor> servoCommand,SerialCommand serialCommand) {
        this.servoService = servoService;
        this.servoCommand = servoCommand;
        this.serialCommand = serialCommand;
    }

    @GetMapping
    public List<ServoLog> findAll() {
        return this.servoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServoLog> getServoLog(@PathVariable int id) {
        ServoLog servoLog = servoService.findById(id);
        if (servoLog == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(servoLog);
    }

    @PostMapping
    public ResponseEntity<ServoLog> createServo(@RequestBody ServoLog servoLog) throws OperationNotSupportedException {
        //TODO refactor using DTO
        servoLog.setId(0);

        ServoMotor currentServoLog = this.servoCommand.createServo(
                servoLog.getServoMotorName()
                , servoLog.getAngle());

        if(currentServoLog == null){
            return ResponseEntity.badRequest().build();
        }

      String theCommand = currentServoLog.toString();

      //send  the command to arduino
        this.serialCommand.sendCommand(theCommand);

        //save  servoLog in db
        this.servoService.save(servoLog);
        return ResponseEntity.status(HttpStatus.CREATED).body(servoLog);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ServoLog> patchServoLog(@PathVariable int id
            , @RequestBody Map<String, Object> patchPayload) throws JsonMappingException {

        ServoLog targetServo = this.servoService.findById(id);

        if (targetServo == null) {
            return ResponseEntity.notFound().build();
        }

        if (patchPayload.containsKey("id")) {
            return ResponseEntity.badRequest().build();
        }

        JsonMapper jsonMapper = jsonMapperSingleton();
        ServoLog patchedServoLog = jsonMapper.updateValue(targetServo, patchPayload);

        ServoLog savedServoLog = this.servoService.save(patchedServoLog);

        return ResponseEntity.status(HttpStatus.OK).body(savedServoLog);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServoLog(@PathVariable int id) {
        ServoLog servoLog = this.servoService.findById(id);

        if (servoLog == null) {
            return ResponseEntity.notFound().build();
        }

        this.servoService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}



