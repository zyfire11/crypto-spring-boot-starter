package com.zy.crypto.starter.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @description:
 * @author: zhouyi
 * @create: 2019/9/29
 */
@Data
@ConfigurationProperties(prefix = "crypto")
public class CryptoProperties {
  private String charset;
  private final CryptoProperties.Decrypt decrypt = new CryptoProperties.Decrypt();
  private final CryptoProperties.Encrypt encrypt = new CryptoProperties.Encrypt();

  public static class Decrypt{
    private Boolean use;

    public Boolean getUse() {
      return use;
    }

    public void setUse(Boolean use) {
      this.use = use;
    }
  }

  public static class Encrypt{
    private Boolean use;

    public Boolean getUse() {
      return use;
    }

    public void setUse(Boolean use) {
      this.use = use;
    }
  }
}
