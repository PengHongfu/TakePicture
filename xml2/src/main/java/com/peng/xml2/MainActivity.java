package com.peng.xml2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.InputStream;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        try {
            TextView tv_weather = (TextView) findViewById(R.id.tv_weather);
            //获取资产的管理者 通过上下文
            InputStream inputStream = getAssets().open("weather.xml");
            //调用我们定义的解析xml方法
            List<Channel> weatherlists = WeatherParser.parserXml(inputStream);
            StringBuffer sb = new StringBuffer();
            for (Channel channel : weatherlists) {
                sb.append(channel.toString());
            }
            //把数据显示到textview上
            tv_weather.setText(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
