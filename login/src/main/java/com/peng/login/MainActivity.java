package com.peng.login;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private EditText Username;
    private EditText Userpassword;
    private CheckBox Ischeck;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Username = (EditText) findViewById(R.id.et_username);
        Userpassword = (EditText) findViewById(R.id.et_userpassword);
        Ischeck = (CheckBox) findViewById(R.id.cb_ischeck);
        System.out.print("------------->>>" + Environment.getExternalStorageDirectory());
        //读取info.txt
       Map<String,String> maps = UserInfoUtils.readInfo(MainActivity.this);
        if(maps!=null){
            //把name和pwd取出来
            String name = maps.get("name");
            String pwd = maps.get("pwd");
            //显示name和pwd
            Username.setText(name);
            Userpassword.setText(pwd);

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
                //把用户名和密码存起来
                boolean result = UserInfoUtils.saveInfo(MainActivity.this,name, pwd);
                if (result) {
                    Toast.makeText(MainActivity.this, "保存成功", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "保存失败", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(MainActivity.this, "勾选cb", Toast.LENGTH_LONG).show();
            }
        }
    }
}
