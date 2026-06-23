package com.robot_arm.demo.services.servoService;

import com.robot_arm.demo.entity.ServoLog;
import com.robot_arm.demo.repository.ServoRepository;
import com.robot_arm.demo.enums.ServoMotorName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServoServiceImpl implements ServoLogService<ServoLog>,ServoCommand<ServoMotor> {
    private ServoMotorFactory servoFactory;
    private ServoRepository servoRepository;

    @Autowired
    public ServoServiceImpl(ServoMotorFactory servoFactory, ServoRepository servoRepository) {
        this.servoFactory = servoFactory;
        this.servoRepository = servoRepository;
    }


    @Override
    public List<ServoLog> findAll() {
        return this.servoRepository.findAll();
    }

    @Override
    public ServoLog findById(int id) {
        return servoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ServoLog not found"));
    }

    @Override
    public ServoLog save(ServoLog servoLog) {
        return this.servoRepository.save(servoLog);
    }

    @Override
    public void deleteById(int id) {
        this.servoRepository.deleteById(id);
    }

    @Override
    public ServoMotor createCommand(ServoMotorName servoName, int angle) {
       return this.servoFactory.getServoMotor(servoName, angle);
    }
}
