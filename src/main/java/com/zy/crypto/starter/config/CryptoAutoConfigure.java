package com.zy.crypto.starter.config;

import com.zy.crypto.starter.service.Crypto;
import com.zy.crypto.starter.service.impl.symmetry.AESCrypto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * @author: zhouyi
 * @create: 2019/9/29
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(CryptoProperties.class)
public class CryptoAutoConfigure {

  @Autowired
  CryptoProperties properties;

  @Bean
  @ConditionalOnMissingBean
  public Crypto getBean(CryptoProperties properties){
    //todo 根据加密算法进行选择算法实现
    Crypto crypto = new AESCrypto();
    return crypto;
  }
}
