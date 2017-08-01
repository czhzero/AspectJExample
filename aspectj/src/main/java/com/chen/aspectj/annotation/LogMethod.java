package com.chen.aspectj.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.CLASS;

/**
 * Created by chenzhaohua on 17-8-1.
 */
@Target({METHOD})
@Retention(CLASS)
public @interface LogMethod {
}
