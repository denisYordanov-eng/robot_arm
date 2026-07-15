package com.robot_arm.demo.restControllers;


import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.robot_arm.demo.entity.ServoLog;
import com.robot_arm.demo.services.serialService.SerialCommand;
import com.robot_arm.demo.services.servoService.ServoCommand;
import com.robot_arm.demo.services.servoService.ServoLogService;
import com.robot_arm.demo.services.servoService.servoMotors.ServoMotor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.naming.OperationNotSupportedException;
import java.util.List;
import java.util.Map;

import static com.robot_arm.demo.jsonMapperSingleton.JsonMapperSingleton.jsonMapperSingleton;

@RestController
@RequestMapping("/api")
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

    @GetMapping("/servo")
    public List<ServoLog> findAll() {
        return this.servoService.findAll();
    }

    @GetMapping("/servo/{id}")
    public ServoLog getServoLog(@PathVariable int id) {
        ServoLog servoLog = servoService.findById(id);
        if (servoLog == null) {
            throw new IllegalArgumentException("servo not found");
        }

        return servoLog;
    }

    @PostMapping("/servo")
    public ServoLog createServo(@RequestBody ServoLog servoLog) throws OperationNotSupportedException {
        //TODO refactor using DTO
        servoLog.setId(0);

        ServoMotor currentServoLog = this.servoCommand.createServo(
                servoLog.getServoMotorName()
                , servoLog.getAngle());

      String theCommand = currentServoLog.toString();

      //send  the command to arduino
        this.serialCommand.sendCommand(theCommand);

        //save  servoLog in db
        return this.servoService.save(servoLog);
    }

    @PatchMapping("/servo/{id}")
    public ServoLog patchServoLog(@PathVariable int id
            , @RequestBody Map<String, Object> patchPayload) throws JsonMappingException {

        ServoLog targetServo = this.servoService.findById(id);

        if (targetServo == null) {
            throw new IllegalArgumentException("ServoLog not found");
        }

        if (patchPayload.containsKey("id")) {
            throw new RuntimeException("ServoLog id not allowed in request body - " + id);
        }

        JsonMapper jsonMapper = jsonMapperSingleton();
        ServoLog patchedServoLog = jsonMapper.updateValue(targetServo, patchPayload);

        ServoLog savedServoLog = this.servoService.save(patchedServoLog);

        return savedServoLog;
    }

    @DeleteMapping("/servo/{id}")
    public String deleteServoLog(@PathVariable int id) {
        ServoLog servoLog = this.servoService.findById(id);

        if (servoLog == null) {
            throw new RuntimeException("ServoLog not found with id - " + id);
        }

        this.servoService.deleteById(id);

        return "ServoLog deleted successfully";
    }


}



