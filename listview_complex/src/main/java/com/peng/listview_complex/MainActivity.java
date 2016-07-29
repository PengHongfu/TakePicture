package com.peng.listview_complex;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(R.id.lv);
        listView.setAdapter(new MyAdapter());

    }

    private class MyAdapter extends BaseAdapter {

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
            //把自己定义的布局转换成一个view对象
            View view1;
            if (view == null) {
                //创建新的view对象,可以通过打气筒把一个布局资源转换成一个view对象
                //各种布局都继承Viewgroup

                //1.第一种获取打气筒的方法
                //view1 = View.inflate(getApplicationContext(), R.layout.item, null);
                //2.第二种获取打气筒的方法
                //view1 = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item,null);
                //第三种获取打气筒的方法
                LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                 view1 =inflater.inflate(R.layout.item, null);
            } else {
                //复用历史缓存对象
                view1 = view;

            }
            return view1;
        }
    }
}
