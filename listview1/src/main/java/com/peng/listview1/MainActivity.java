package com.peng.listview1;

        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.BaseAdapter;
        import android.widget.ListAdapter;
        import android.widget.ListView;
        import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //ListView
        lv = (ListView) findViewById(R.id.lv);

        //显示数据 数据来自适配器ListAdapter
        lv.setAdapter(new MyListAdapter());
    }
    //定义listView的数据适配器
    private  class MyListAdapter extends BaseAdapter{

        //有多少数据需要显示
        @Override
        public int getCount() {
            return 10000000;
        }
        //返回指定position位置对应的对象
        @Override
        public Object getItem(int i) {
            return null;
        }
        //返回position位置对应的id
        @Override
        public long getItemId(int i) {
            return 0;
        }

        /**
         * 获取一个view,用来显示listview的数据,会作为listview的一个条目出现
         *
         */
        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            TextView tv;
            if(view==null){
             tv = new TextView(MainActivity.this);
                System.out.println("创建新的View对象---"+i);
            }else{
                System.out.println("复用的历史缓存对象---"+i);
                tv = (TextView) view;
            }
            tv.setText("哈哈"+i);
            return tv;
        }
    }
}
