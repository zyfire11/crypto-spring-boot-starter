package com.zy.crypto.starter.config.advice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.zy.crypto.starter.service.impl.symmetry.AESCrypto;
import com.zy.crypto.starter.utils.CryptoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.gson.GsonBuilderCustomizer;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.io.IOException;

/**
 * @description:
 * @author: zhouyi
 * @create: 2019/9/25
 */
@RestControllerAdvice
@ConditionalOnProperty(prefix = "crypto.encrypt", name = "use", havingValue = "true", matchIfMissing = false)
public class EncryptResponseBodyAdvice implements ResponseBodyAdvice {

  @Value("${crypto.charset}")
  private String charset = "utf-8";

  @Autowired
  ObjectMapper objectMapper;

  @Override
  public boolean supports(MethodParameter methodParameter, Class aClass) {
    return true;
  }



  @Override
  public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
    if (!CryptoUtil.needEncrypt(methodParameter)){
      return o;
    }

    String retData = null;
    try {
      //将对象转成json字符串，并替换其中data部分数据
      String jsonStr = objectMapper.writeValueAsString(o);
      JsonNode node = objectMapper.readTree(jsonStr);
      if (!node.has("data")){
        return o;
      }

      if (node.at("/data").isNull()){
        return o;
      }

      ObjectNode topNode = (ObjectNode) node;
      String data = topNode.get("data").toString();
      String encryptData = new AESCrypto().encrypt(data, charset);
      topNode.put("data", encryptData);
      retData = objectMapper.writeValueAsString(node);

      //将json字符串转回原对象（处理返回值为String时，前后多带了“问题）
      Class retClass = o.getClass();
      return objectMapper.readValue(retData, retClass);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return o;
  }
}
