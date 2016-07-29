package com.peng.sd;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    private TextView usable_size;
    private TextView total_size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usable_size = (TextView) findViewById(R.id.textView2);
        total_size = (TextView) findViewById(R.id.textView);
        //获取sd卡总大小和可用空间
        File file = Environment.getExternalStorageDirectory();
        long totalSpace= file.getTotalSpace();//总大小
        long usableSpace = file.getUsableSpace();//可用控件
        //转换成m格式
        String formatTotalSpace = android.text.format.Formatter.formatFileSize(this,totalSpace);
        String formatUsableSpace = android.text.format.Formatter.formatFileSize(this,usableSpace);
        //展示到textview
        usable_size.setText("可用空间"+formatUsableSpace);
        total_size.setText("总大小"+formatTotalSpace);

    }
}
