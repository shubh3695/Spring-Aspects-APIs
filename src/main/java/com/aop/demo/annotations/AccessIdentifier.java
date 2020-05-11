package com.aop.demo.annotations;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface AccessIdentifier {
    int duration() default 1; // denotes access for 'Y' duration

    TimeFormat durationFormat() default TimeFormat.SECONDS;
}
