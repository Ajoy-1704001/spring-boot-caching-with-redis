package com.deb.data_cache;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MethodExecutionMetric {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Pointcut(value = "within(@org.springframework.web.bind.annotation.RestController *)")
    public void serviceClassMethods(){}

    @Around(value = "serviceClassMethods()")
    public Object calculateMethodExecution(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        log.info("Execution of method {}: {} ms",proceedingJoinPoint.getSignature().getName(), elapsedTime);

        return result;
    }
}
