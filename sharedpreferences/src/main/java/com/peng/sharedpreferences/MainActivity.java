package com.peng.sharedpreferences;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {
    private EditText Username;
    private EditText Userpassword;
    private CheckBox Ischeck;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化sp 实例
        SharedPreferences sp =getSharedPreferences("config",0);

        Username = (EditText) findViewById(R.id.et_username);
        Userpassword = (EditText) findViewById(R.id.et_userpassword);
        Ischeck = (CheckBox) findViewById(R.id.cb_ischeck);

        Ischeck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            //勾选记住密码时
            public void onCheckedChanged(CompoundButton compoundButton, boolean ischeckd) {
                SharedPreferences sp = getSharedPreferences("config",0);
                SharedPreferences.Editor editor = sp.edit();
                editor.putBoolean("ischeckd",ischeckd);
                editor.commit();
            }
        });

        //页面一开始显示的数据
        String name =sp.getString("name","");
        String pwd = sp.getString("pwd","");
        Username.setText(name);
        Userpassword.setText(pwd);

        Boolean  ischeckd=sp.getBoolean("ischeckd",false);
        if(ischeckd){
            Ischeck.setChecked(ischeckd);
        }


    }

    public void login(View view) throws IOException {
        //获取用户名
        String name = Username.getText().toString().trim();
        String pwd = Userpassword.getText().toString().trim();

        //判断name 和pwd 是否为空
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(pwd)) {
            Toast.makeText(MainActivity.this, "用户名或密码不能为空", Toast.LENGTH_LONG).show();
        } else {
            System.out.println("待续....");
            if (Ischeck.isChecked()) {

                //使用SharedPreferences保存存数据,拿到sp的实例,生成xml文件
                SharedPreferences sp = getSharedPreferences("config",0);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("name",name);
                editor.putString("pwd",pwd);
                
                editor.commit();

            } else {
                Toast.makeText(MainActivity.this, "勾选记住密码", Toast.LENGTH_LONG).show();
            }
        }
    }
}
