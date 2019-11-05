package com.zy.crypto.starter.utils;

import java.io.*;

/**
 * @description: io操作工具类
 * @author: zhouyi
 * @create: 2019/9/25
 */
public class IOUtil {

  public static String read(InputStream inputStream, String charset){
    StringBuilder sb = new StringBuilder();
    BufferedReader reader = null;
    try {
      reader = new BufferedReader(new InputStreamReader(inputStream, charset));
      String line;
      while ((line = reader.readLine()) != null){
        sb.append(line + "\n");
      }
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }finally {
      try {
        reader.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return sb.toString();
  }
}
