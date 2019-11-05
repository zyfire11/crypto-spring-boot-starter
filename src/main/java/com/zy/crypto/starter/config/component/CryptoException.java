package com.zy.crypto.starter.config.component;

/**
 * @description:
 * @author: zhouyi
 * @create: 2019/9/29
 */
public class CryptoException extends RuntimeException {

  public CryptoException(){
    super();
  }

  public CryptoException(String message){
    super(message);
  }

  public CryptoException(String message, Throwable cause){
    super(message, cause);
  }

  public CryptoException(Throwable cause){
    super(cause);
  }
}
