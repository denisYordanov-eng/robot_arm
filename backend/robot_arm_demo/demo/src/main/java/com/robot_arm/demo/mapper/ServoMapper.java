package com.robot_arm.demo.mapper;

import com.robot_arm.demo.dto.ServoDto;
import com.robot_arm.demo.entity.ServoLog;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ServoMapper {
    ServoLog toEntity(ServoDto servoDto);

    ServoDto toDto(ServoLog servoLog);

    List<ServoDto> toDtoList(List<ServoLog> servoLogs);

}
