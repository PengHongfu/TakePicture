package com.peng.news_client;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import image.SmartImageView;

public class MainActivity extends AppCompatActivity {
    private ListView lv;
    private List<News> newsLists;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (ListView) findViewById(R.id.lv);

        //准备Listview要显示的数据
        initListData();

    }
    private void initListData() {
         new Thread(){
             @Override
             public void run() {
                 //去服务器取数据http://192.168.1.47:8080/news.xml
                 try {
                     String path = "http://192.168.1.47:8080/news.xml";
                     //创建一个URL 对象指定我们要访问的网址(路径)
                     URL url = new URL(path);
                     //拿到httpurlconnection对象,用于发射或接收数据
                     HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                     //设置发送get请求
                     conn.setRequestMethod("GET");//get要求大写,默认get请求
                     //设置请求超时时间
                     conn.setConnectTimeout(5000);
                     //获取服务器返回的状态码
                     int code = conn.getResponseCode();
                     //code 为200,则请求成功
                     if(code ==200){
                         //得到流
                         InputStream inputStream =conn.getInputStream();

                         //解析xml 抽出来一个类
                          newsLists =XmlParaseUtils.parseXml(inputStream);
                         runOnUiThread(new Runnable() {
                             //更新ui
                             @Override
                             public void run() {
                                 lv.setAdapter(new MyAdapter());
                             }
                         });
                     }
                 } catch (Exception e) {
                     e.printStackTrace();
                 }
             }
         }.start();


    }
    //定义数据适配器
    private  class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return newsLists.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View view1;
            if(view ==null){
                view1 = View.inflate(getApplicationContext(),R.layout.item,null);
            }else{
                view1 = view;
            }
            //找到控件,显示数据
            SmartImageView iv_icon = (SmartImageView) view1.findViewById(R.id.iv_icon);
            TextView tv_desc = (TextView) view1.findViewById(R.id.tv_desc);
            TextView tv_title = (TextView) view1.findViewById(R.id.tv_title);
            TextView tv_type = (TextView) view1.findViewById(R.id.tv_type);

            //展示图片
             String imageUrl = newsLists.get(i).getImage();
            iv_icon.setImageUrl(imageUrl);

            //显示数据
            tv_title.setText(newsLists.get(i).getTitle());
            tv_desc.setText(newsLists.get(i).getDescription());
            String typee = newsLists.get(i).getType();
            String comment = newsLists.get(i).getComment();
            int type = Integer.parseInt(typee);
            switch (type){
                case 1:
                    tv_type.setText(comment+"国内");
                    break;
                case 2:
                    tv_type.setText("跟帖");
                    break;
                case 3:
                    tv_type.setText("国外");
                    break;
                case 4:
                    tv_type.setText("国我");
                    break;
            }
            return view1;
        }
    }
}
