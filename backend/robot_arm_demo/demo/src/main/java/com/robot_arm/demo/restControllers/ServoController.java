package com.robot_arm.demo.restControllers;


import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.robot_arm.demo.dto.CreateServoDto;
import com.robot_arm.demo.dto.ServoDto;
import com.robot_arm.demo.entity.ServoLog;
import com.robot_arm.demo.mapper.CreateServoMapper;
import com.robot_arm.demo.services.serialService.SerialCommand;
import com.robot_arm.demo.services.servoService.ServoCommand;
import com.robot_arm.demo.services.servoService.ServoLogService;
import com.robot_arm.demo.services.servoService.servoMotors.ServoMotor;
import com.robot_arm.demo.mapper.ServoMapper;
import jakarta.validation.Valid;
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
    private ServoMapper servoMapper;
    private CreateServoMapper createServoMapper;

    @Autowired
    public ServoController(ServoLogService<ServoLog> servoService
            , ServoCommand<ServoMotor> servoCommand,SerialCommand serialCommand,
                           ServoMapper servoMapper,CreateServoMapper createServoMapper) {
        this.servoService = servoService;
        this.servoCommand = servoCommand;
        this.serialCommand = serialCommand;
        this.servoMapper = servoMapper;
        this.createServoMapper = createServoMapper;
    }

    @GetMapping
    public List<ServoDto> findAll() {
        List<ServoLog> servoLogs = this.servoService.findAll();

        return this.servoMapper.toDtoList(servoLogs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServoDto> getServoLog(@PathVariable int id) {
        ServoLog servoLog = servoService.findById(id);
        if (servoLog == null) {
            return ResponseEntity.notFound().build();
        }
        ServoDto servoDto = this.servoMapper.toDto(servoLog);
        return ResponseEntity.ok(servoDto);
    }

    @PostMapping
    public ResponseEntity<ServoDto> createServo(@Valid @RequestBody CreateServoDto createServoDto) throws OperationNotSupportedException {

        ServoLog servoLog = this.createServoMapper.toEntity(createServoDto);

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

        return ResponseEntity.status(HttpStatus.CREATED).body(this.servoMapper.toDto(servoLog));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ServoDto> patchServoLog(@PathVariable int id
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

        return ResponseEntity.status(HttpStatus.OK).body(this.servoMapper.toDto(savedServoLog));
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



