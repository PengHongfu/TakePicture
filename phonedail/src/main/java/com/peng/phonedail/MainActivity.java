package com.peng.phonedail;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText etc_number;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        etc_number = (EditText) findViewById(R.id.editText);
        button = (Button) findViewById(R.id.button);
    }

    private void callphone() {
        //去输入字符的空格
        String number = etc_number.getText().toString().trim();
        if("".equals(number)){
            Toast.makeText(MainActivity.this,"number不能为空",Toast.LENGTH_LONG).show();
            return;
        }
        //创建意图对象
        Intent intent = new Intent();
        //设置动作
        intent.setAction(Intent.ACTION_CALL);
        //设置要拨打的数据
        /**
         * url:统一资源定位符www.baidu.com
         * uri:统一资源标识符,自己定义的路径,想代表什么都可以
         */
        intent.setData(Uri.parse("tel:"+number));
        //开启意图
        startActivity(intent);
    }
}
