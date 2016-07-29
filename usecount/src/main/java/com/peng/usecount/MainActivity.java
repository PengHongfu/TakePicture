package com.peng.usecount;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView show;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        show = (TextView) findViewById(R.id.tv_show);

        SharedPreferences sp = getSharedPreferences("count",MODE_WORLD_READABLE);

        //读取count里的数据,通过吐司显示
        int count = sp.getInt("count",0);
        Toast.makeText(this,"程序以前被使用"+count,Toast.LENGTH_LONG).show();
        //填入数据
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("count",++count);
        editor.commit();
    }
}
