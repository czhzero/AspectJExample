package com.chen.aspectj.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.METHOD;

/**
 * Created by chenzhaohua on 17-8-2.
 */
@Target({METHOD, CONSTRUCTOR})
@Retention(RetentionPolicy.CLASS)
public @interface Trace {
}


