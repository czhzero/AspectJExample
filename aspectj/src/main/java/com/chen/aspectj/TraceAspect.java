package com.chen.aspectj;

import android.text.TextUtils;
import android.util.Log;

import com.chen.aspectj.annotation.Trace;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import java.util.concurrent.TimeUnit;

/**
 * Created by chenzhaohua on 17-8-2.
 */
@Aspect
public class TraceAspect {

    private static final String POINTCUT_METHOD = "execution(@com.chen.aspectj.annotation.Trace * *(..))";

    private static final String POINTCUT_CONSTRUCTOR = "execution(@com.chen.aspectj.annotation.Trace *.new(..))";

    @Pointcut(POINTCUT_METHOD)
    public void methodAnnotatedWithTrace() {
    }

    @Pointcut(POINTCUT_CONSTRUCTOR)
    public void constructorAnnotatedTrace() {
    }

    @Around("methodAnnotatedWithTrace() || constructorAnnotatedTrace()")
    public Object traceMethod(final ProceedingJoinPoint joinPoint) throws Throwable {


        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();

        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object result = joinPoint.proceed();
        stopWatch.stop();


        if (TextUtils.isEmpty(className)) {
            className = "Anonymous class";
        }

        Log.i(className, buildLogMessage(methodName, stopWatch.getTotalTimeMillis()));

        return result;
    }

    /**
     * Create a log message.
     *
     * @param methodName A string with the method name.
     * @param methodDuration Duration of the method in milliseconds.
     * @return A string representing message.
     */
    private static String buildLogMessage(String methodName, long methodDuration) {
        StringBuilder message = new StringBuilder();
        message.append(methodName);
        message.append("()");
        message.append(" take ");
        message.append("[");
        message.append(methodDuration);
        message.append("ms");
        message.append("]");
        return message.toString();
    }

    public class StopWatch {

        private long startTime;
        private long endTime;
        private long elapsedTime;

        public StopWatch() {
        }

        private void reset() {
            startTime = 0;
            endTime = 0;
            elapsedTime = 0;
        }

        public void start() {
            reset();
            startTime = System.nanoTime();
        }

        public void stop() {
            if (startTime != 0) {
                endTime = System.nanoTime();
                elapsedTime = endTime - startTime;
            } else {
                reset();
            }
        }

        public long getTotalTimeMillis() {
            return (elapsedTime != 0) ? TimeUnit.NANOSECONDS.toMillis(endTime - startTime) : 0;
        }
    }

}
