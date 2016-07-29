package com.peng.image_show;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private ImageView iv;
    private EditText et_path;
    /*private Handler handle = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            Bitmap bitmap = (Bitmap) msg.obj;
            iv.setImageBitmap(bitmap);
        }
    };*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_path = (EditText) findViewById(R.id.et_path);
        iv = (ImageView) findViewById(R.id.iv);
    }
    public void click(View view){
        new Thread() {
        public void run(){
            try {
                //获取图片路径
                String path = et_path.getText().toString().trim();
                //File file = new File(getCacheDir(), Base64.encodeToString(path.getBytes(),Base64.DEFAULT);
                File file = new File(getCacheDir(),"test.png");
                if (file.exists()&&file.length()>0){
                    //使用缓存图片
                    System.out.println("---->>>>>>>>>>使用缓存的图片");
                   final Bitmap cacheBitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                 /*   Message msg = Message.obtain();
                    msg.obj = cacheBitmap;
                    handle.sendMessage(msg);*/
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            iv.setImageBitmap(cacheBitmap);
                        }
                    });
                }else{
                    System.out.println("---->>>>>>>>>>使用网络的图片");
                    //第一次访问连接网络

                //创建url对象
                URL url = new URL(path);
                //获取httpurlconnection
                HttpURLConnection conn =(HttpURLConnection)url.openConnection();
                //连接方式
                conn.setRequestMethod("GET");
                conn.setConnectTimeout(5000);
                //获取服务器的状态码
                int code = conn.getResponseCode();
                if(code == 200){
                    //获取图片的数据,不管什么数据,都是流的形式返回
                    InputStream in = conn.getInputStream();
                     file = new File(getCacheDir(),"test.png");
                    FileOutputStream fos = new FileOutputStream(file);
                    int len = -1;
                    byte[] buffer = new byte[1024];
                    while((len =in.read(buffer))!=-1){
                        fos.write(buffer,0,len);
                    }
                    fos.close();
                    in.close();

                    //获取位图工厂,获取bitmap
                    final Bitmap bitmap =BitmapFactory.decodeFile(file.getAbsolutePath());

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //run方法一定执行在UI线里
                            iv.setImageBitmap(bitmap);
                        }
                    });
                    /*Message msg =Message.obtain();//msg的静态方法,减少对象的创建
                    msg.obj =bitmap;
                    handle.sendMessage(msg);//发消息*/

                }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        }.start();


    }
}
