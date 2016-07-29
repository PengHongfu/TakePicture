package com.peng.login;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Peng on 2016/7/25.
 */
public class UserInfoUtils {
    public static boolean saveInfo(Context context,String username, String pwd) throws IOException {
        try {
           // String path = context.getFilesDir().getPath();
            String result = username + "##" + pwd;
           /* //存储位置
            //File file = new File("/data/data/com.peng.login/info.txt");
            File file  = new File(path,"info.txt");
            //创建文件输出流
            FileOutputStream fileOutputStream = new FileOutputStream(file);*/
            //通过上下文获取FileOutputStream
            FileOutputStream fos = context.openFileOutput("info.txt",0);
            fos.write(result.getBytes());
            fos.close();
            return true;
        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }

    }

    //读取用户名和密码的信息
    public static Map<String, String> readInfo(Context context) {
        try {
            //String path = context.getFilesDir().getPath();
             FileInputStream fis =context.openFileInput("info.txt");
           Map<String, String> maps = new HashMap<String, String>();
          /*  File file = new File(path,"info.txt");
            FileInputStream fis = null;
            fis = new FileInputStream(file);*/
            BufferedReader bufr = new BufferedReader(new InputStreamReader(fis));
            String content = bufr.readLine();//读取数据
            //切割字符串,封装到map集合中
            String[] splits = content.split("##");
            String name = splits[0];
            String pwd = splits[1];
            //把name 和pwd放入map
            maps.put("name", name);
            maps.put("pwd", pwd);
            fis.close();
            return maps;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
