package com.robot_arm.demo.aspect;

import com.robot_arm.demo.services.serialService.SerialService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.naming.OperationNotSupportedException;

@Aspect
@Component
public class SerialConnectionAspect {

    @Before("execution(* com.robot_arm.demo.services.serialService.SerialServiceImpl.sendCommand(..))")
    public void connectedAdvice(JoinPoint joinPoint) throws OperationNotSupportedException {
        SerialService serialService = (SerialService) joinPoint.getTarget();

        if (!serialService.isConnected()) {
            throw new OperationNotSupportedException("Not connected");
        }

    }
}
