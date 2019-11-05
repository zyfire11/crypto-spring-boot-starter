package com.zy.crypto.starter.annontation;

import java.lang.annotation.*;

/**
 * @description: requestBody解密注解
 * @author: zhouyi
 * @create: 2019/9/25
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DecryptRequest {

  boolean value() default true;
}
