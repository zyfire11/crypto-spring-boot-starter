package com.zy.crypto.starter.annontation;

import com.zy.crypto.starter.config.CryptoProperties;
import com.zy.crypto.starter.config.advice.DecryptRequestBodyAdvice;
import com.zy.crypto.starter.config.advice.EncryptResponseBodyAdvice;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @description: 接口数据安全启动注解
 * @author: zhouyi
 * @create: 2019/10/26
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({
  CryptoProperties.class,
  EncryptResponseBodyAdvice.class,
  DecryptRequestBodyAdvice.class
})
public @interface EnableSecurity {
}
