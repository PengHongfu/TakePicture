package com.peng.file;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import java.io.FileOutputStream;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    //private
    public void click1(View view){
                try {
                    FileOutputStream fos = openFileOutput("private.txt",MODE_PRIVATE);
                    fos.write("private".getBytes());//写入的数据
                    fos.close();
                } catch (Exception e) {
                    e.printStackTrace();
        }
    }
        //append追加
    public void click2(View view){
        try {
            FileOutputStream fos = openFileOutput("append.txt",MODE_APPEND);
            fos.write("append".getBytes());
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //read
    public void click3(View view){
        try {
            FileOutputStream fos = openFileOutput("read.txt",MODE_WORLD_READABLE);
            fos.write("read".getBytes());
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //write
    public void click4(View view){
        try {
            FileOutputStream fos = openFileOutput("write.txt",MODE_WORLD_WRITEABLE);
            fos.write("write".getBytes());
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
