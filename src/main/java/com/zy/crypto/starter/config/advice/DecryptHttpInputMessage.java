package com.zy.crypto.starter.config.advice;

import com.zy.crypto.starter.service.impl.symmetry.AESCrypto;
import com.zy.crypto.starter.utils.IOUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @description:
 * @author: zhouyi
 * @create: 2019/9/25
 */
public class DecryptHttpInputMessage implements HttpInputMessage {

  private HttpInputMessage inputMessage;
  private String charset;

  public DecryptHttpInputMessage(HttpInputMessage message, String charset){
    this.inputMessage = message;
    this.charset = charset;
  }

  @Override
  public InputStream getBody() throws IOException {
    String content = IOUtil.read(inputMessage.getBody(), charset);
    String decryptBody = new AESCrypto().decrypt(content, charset);
    return new ByteArrayInputStream(decryptBody.getBytes(charset));
  }

  @Override
  public HttpHeaders getHeaders() {
    return inputMessage.getHeaders();
  }
}
