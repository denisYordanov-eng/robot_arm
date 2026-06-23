package com.robot_arm.demo.repository;

import com.robot_arm.demo.entity.ServoLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServoRepository extends JpaRepository<ServoLog, Integer> {
}
