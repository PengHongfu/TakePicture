package com.peng.html_look;

import java.io.InputStream;
import java.io.ByteArrayOutputStream;
/**
 *
 * 读取流中的数据
 *
 *  inputStream输入流转换成一个String
 *Created by Peng on 2016/7/28.
 */

public class StreamTools {
    public static String readStream(InputStream inputStream) throws Exception{
        //定义内存输出流
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = -1;
        while((len = inputStream.read(buffer)) != -1){
            outStream.write(buffer,0,len);
        }
        inputStream.close();
        String content = new String(outStream.toByteArray());
        return  content;

    }
}
