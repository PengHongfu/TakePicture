package com.peng.listview_magic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView lv = (ListView) findViewById(R.id.lv);

        //设置listview的数据适配器
        lv.setAdapter(new MyAdapter());

    }
    private  class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return 6;
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
            //View convertView
            TextView textView;
                System.out.println("getview----"+i);
            if(view ==null){
                textView = new TextView(MainActivity.this);
            }else{
                textView = (TextView) view;
            }
                textView.setText("your are super"+i);
            return textView;
        }
    }
}
