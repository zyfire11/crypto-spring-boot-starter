package com.zy.crypto.starter.utils;

import com.zy.crypto.starter.annontation.DecryptRequest;
import com.zy.crypto.starter.annontation.EncryptResponse;
import org.springframework.core.MethodParameter;

/**
 * @description: 根据接口传参是否有加解密注解判断是否需要进行加解密
 * @author: zhouyi
 * @create: 2019/9/25
 */
public class CryptoUtil {

  //根据类或者方法上面是否有@EncryptResponse注解进行是否加密操作
  public static boolean needEncrypt(MethodParameter para){
    boolean encrypt = false;
    boolean classPresentAnno = para.getContainingClass().isAnnotationPresent(EncryptResponse.class);
    if (classPresentAnno){
      encrypt = para.getContainingClass().getAnnotation(EncryptResponse.class).value();
      if (!encrypt){
        return false;
      }
    }
    boolean methodPresentAnno = para.getMethod().isAnnotationPresent(EncryptResponse.class);
    if (methodPresentAnno){
      encrypt = para.getMethod().getAnnotation(EncryptResponse.class).value();
    }
    return encrypt;
  }

  //根据类或者方法上面是否有@DecryptRequest注解进行是否解密操作
  public static boolean needDecrypt(MethodParameter para){
    boolean decrypt = false;
    boolean classPresentAnno = para.getContainingClass().isAnnotationPresent(DecryptRequest.class);
    if (classPresentAnno){
      decrypt = para.getContainingClass().getAnnotation(DecryptRequest.class).value();
      if (!decrypt){
        return false;
      }
    }
    boolean methodPresentAnno = para.getMethod().isAnnotationPresent(DecryptRequest.class);
    if (methodPresentAnno){
      decrypt = para.getMethod().getAnnotation(DecryptRequest.class).value();
    }
    return decrypt;
  }
}
