package com.zy.crypto.starter.config.advice;

import com.zy.crypto.starter.utils.CryptoUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonInputMessage;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * @description:
 * @author: zhouyi
 * @create: 2019/9/25
 */
@RestControllerAdvice
@ConditionalOnProperty(prefix = "crypto.decrypt", name = "use", havingValue = "true", matchIfMissing = false)
public class DecryptRequestBodyAdvice extends RequestBodyAdviceAdapter {

  @Value("${crypto.charset}")
  private String charset = "utf-8";

  @Override
  public boolean supports(MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
    return AbstractJackson2HttpMessageConverter.class.isAssignableFrom(aClass);
  }

  @Override
  public HttpInputMessage beforeBodyRead(HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) throws IOException {
    //进行解密
    if (CryptoUtil.needDecrypt(methodParameter)) {
      return new DecryptHttpInputMessage(httpInputMessage, charset);
    }
    return new MappingJacksonInputMessage(httpInputMessage.getBody(), httpInputMessage.getHeaders());
  }

}
