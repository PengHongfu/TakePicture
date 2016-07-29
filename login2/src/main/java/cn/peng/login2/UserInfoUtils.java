package cn.peng.login2;

import android.content.Context;
import android.os.Environment;

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
            String result = username + "##" + pwd;

           //存储位置 sdcard
            String sdpath = Environment.getExternalStorageDirectory().getPath();
            File  file = new File(sdpath,"haha.txt");

            //File file = new File("mnt/sdcard/Anfoo.txt");
            //创建文件输出流

            FileOutputStream fos = new FileOutputStream(file);
           // 通过上下文获取FileOutputStream

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
            Map<String, String> maps = new HashMap<String, String>();
            String sdpath = Environment.getExternalStorageDirectory().getPath();
            File file = new File(sdpath,"haha.txt");

            FileInputStream fis = new FileInputStream(file);
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
