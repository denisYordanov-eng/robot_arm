package com.robot_arm.demo.services.servoService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.robot_arm.demo.enums.ServoMotorName;

import static com.robot_arm.demo.jsonMapperSingleton.JsonMapperSingleton.jsonMapperSingleton;

public abstract class ServoMotor implements Command {
    private ServoMotorName servoMotorName;
    private int angle;

    protected ServoMotor(ServoMotorName servoMotorName, int angle) {
        this.servoMotorName = servoMotorName;
        setAngle(angle);
    }

    public ServoMotorName getServoMotorName() {
        return this.servoMotorName;
    }
    public int getAngle() {
        return this.angle;
    }

    @Override
    public void setAngle(int angle) {
        this.angle = angle;
    }

    @Override
    public String createCommand() {
        try {
            JsonMapper mapper = jsonMapperSingleton();
            return mapper.writeValueAsString(this);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }


}
