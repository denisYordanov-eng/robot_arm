package com.robot_arm.demo.mapper;

import com.robot_arm.demo.dto.CreateServoDto;
import com.robot_arm.demo.dto.ServoDto;
import com.robot_arm.demo.entity.ServoLog;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CreateServoMapper {
    ServoLog toEntity(CreateServoDto dto);
    ServoDto toDto(ServoLog servoLog);
}
