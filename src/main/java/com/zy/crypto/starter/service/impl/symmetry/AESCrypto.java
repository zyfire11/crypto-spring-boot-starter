package com.zy.crypto.starter.service.impl.symmetry;

import com.zy.crypto.starter.config.component.CryptoException;
import com.zy.crypto.starter.service.Crypto;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;

/**
 * @description: AES加解密实现
 * @author: zhouyi
 * @create: 2019/9/29
 */
public class AESCrypto implements Crypto {
  private KeyGenerator keygen;

  private String seed = "zhouyi";

  public AESCrypto(String seed){
    this.seed = seed;
  }

  public AESCrypto(){}

  @Override
  public byte[] decrypt(byte[] src) {
    try {
      //1.构造密钥生成器，指定为AES算法,不区分大小写
      keygen = KeyGenerator.getInstance("AES");
      SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG" );
      secureRandom.setSeed(this.seed.getBytes("UTF-8"));
      //2.根据ecnodeRules规则初始化密钥生成器
      //生成一个128位的随机源,根据传入的字节数组
      keygen.init(128, secureRandom);
      //3.产生原始对称密钥
      SecretKey original_key = keygen.generateKey();
      //4.获得原始对称密钥的字节数组
      byte[] raw = original_key.getEncoded();
      //5.根据字节数组生成AES密钥
      SecretKey key = new SecretKeySpec(raw, "AES");
      //6.根据指定算法AES自成密码器
      Cipher cipher = Cipher.getInstance("AES");
      //7.初始化密码器，第一个参数为加密(Encrypt_mode)或者解密(Decrypt_mode)操作，第二个参数为使用的KEY
      cipher.init(Cipher.DECRYPT_MODE, key);

      return  cipher.doFinal(src);
    } catch (Exception e) {
      throw new CryptoException(e);
    }
  }

  @Override
  public byte[] encrypt(byte[] src) {
    try {
      //1.构造密钥生成器，指定为AES算法,不区分大小写
      keygen = KeyGenerator.getInstance("AES");
      //2.根据ecnodeRules规则初始化密钥生成器
      SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG" );
      secureRandom.setSeed(this.seed.getBytes("UTF-8"));
      //生成一个128位的随机源,根据传入的字节数组,不能超过128，否则抛出异常，是因为美国对软件出口的限制
      keygen.init(128,secureRandom);
      //3.产生原始对称密钥
      SecretKey original_key = keygen.generateKey();
      //4.获得原始对称密钥的字节数组
      byte[] raw = original_key.getEncoded();
      //5.根据字节数组生成AES密钥
      SecretKey key = new SecretKeySpec(raw, "AES");
      //6.根据指定算法AES自成密码器
      Cipher cipher = Cipher.getInstance("AES");
      //7.初始化密码器，第一个参数为加密(Encrypt_mode)或者解密解密(Decrypt_mode)操作，第二个参数为使用的KEY
      cipher.init(Cipher.ENCRYPT_MODE, key);
      //9.根据密码器的初始化方式--加密：将数据加密
      byte[] byte_AES = cipher.doFinal(src);
      return byte_AES;
    } catch (Exception e) {
      throw new CryptoException(e);
    }
  }
}
