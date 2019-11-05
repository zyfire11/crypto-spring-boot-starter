package com.zy.crypto.starter.service;

import com.zy.crypto.starter.config.CryptoProperties;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/**
 * @description:
 * @author: zhouyi
 * @create: 2019/9/29
 */
public interface Crypto {


  default String decrypt(String src){
    return decrypt(src, "UTF-8");
  }

  default String decrypt(String src, String charset){
    byte[] bytes = new byte[0];
    try {
      bytes = new BASE64Decoder().decodeBuffer(src);
    } catch (IOException e) {
      e.printStackTrace();
    }
    //具体的解密实现
    byte[] decrypted = decrypt(bytes);
    try {
      return new String(decrypted, charset);
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return null;
  }

  //  具体解密实现
  byte[] decrypt(byte[] bytes);

  default String encrypt(String src){
    return encrypt(src, "UTF-8");
  }

  default String encrypt(String src, String charset){
    try {
      byte[] bytes = src.getBytes(charset);
      byte[] encrypted = encrypt(bytes);
      return new BASE64Encoder().encode(encrypted);
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return null;
  }

  //  具体加密实现
  byte[] encrypt(byte[] bytes);
}
