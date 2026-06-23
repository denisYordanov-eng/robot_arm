package com.robot_arm.demo.services.servoService;

import com.robot_arm.demo.entity.ServoLog;

import java.util.List;

public interface ServoLogService<T extends ServoLog> {
    List<ServoLog> findAll();

    T findById(int id);

    T save(T servoLog);

    void deleteById(int id);
}
