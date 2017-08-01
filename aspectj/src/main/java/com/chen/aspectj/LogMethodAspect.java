package com.chen.aspectj;

import android.util.Log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.util.Arrays;

/**
 * Created by chenzhaohua on 17-8-1.
 */
@Aspect
public class LogMethodAspect {

    @Around("execution(!synthetic * *(..)) && onLogMethod()")
    public Object doLogMethod(final ProceedingJoinPoint joinPoint) throws Throwable {
        return logMethod(joinPoint);
    }

    @Pointcut("@within(com.chen.aspectj.annotation.LogMethod)||@annotation(com.chen.aspectj.annotation.LogMethod)")
    public void onLogMethod() {
    }

    private Object logMethod(final ProceedingJoinPoint joinPoint) throws Throwable {
        Log.d("czh",joinPoint.getSignature().toShortString() + " Args : " + (joinPoint.getArgs() != null ? Arrays.deepToString(joinPoint.getArgs()) : ""));
        Object result = joinPoint.proceed();
        String type = ((MethodSignature) joinPoint.getSignature()).getReturnType().toString();
        Log.d("czh",joinPoint.getSignature().toShortString() + " Result : " + ("void".equalsIgnoreCase(type)?"void":result));
        return result;
    }
}