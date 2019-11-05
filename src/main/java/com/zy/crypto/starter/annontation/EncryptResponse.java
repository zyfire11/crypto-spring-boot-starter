package com.zy.crypto.starter.annontation;

import java.lang.annotation.*;

/**
 * @description: response加密的注解
 * @author: zhouyi
 * @create: 2019/9/25
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EncryptResponse {

  boolean value() default true;
}
