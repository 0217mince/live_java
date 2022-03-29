package com.ke.live.annotation;

import com.ke.live.utils.StatusInfoUtils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 小凡
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RestWrapper {
    String code() default StatusInfoUtils.SUCCESSCODE;
    String msg() default StatusInfoUtils.SUCCESSMSG;
}
