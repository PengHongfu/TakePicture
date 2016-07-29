package com.peng.sqllite_api;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private  MyOpenHelper myOpenHelper;
    private List<Person> lists;
    private ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         lv = (ListView) findViewById(R.id.lv);

        myOpenHelper = new MyOpenHelper(getApplicationContext());
        //定义一个集合保存数据
        lists=new ArrayList<Person>();


        //打开或创建数据库 如果是第一次就是创建
        SQLiteDatabase sqLiteDatabase = myOpenHelper.getWritableDatabase();

        //打开或创建数据库 如果是第一次就是创建 ,磁盘满了返回只读
        // SQLiteDatabase readableDatabase =myOpenHelper.getReadableDatabase();
    }
    public  void click1(View view) {
        //获取数据库对象
        SQLiteDatabase db = myOpenHelper.getWritableDatabase();
        //db.execSQL("insert into info(name,phone) values(?,?)", new Object[] {
         //       "彭鹏","188888787878"});
        //表名  列对应的名字 values对应的值
        ContentValues values =new ContentValues();
        values.put("name","王五");
        values.put("phone","222");
        //返回值为新行的id
        long insert = db.insert("info",null,values);
        db.close();
        if(insert>0){
            Toast.makeText(getApplicationContext(),"添加成功",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getApplicationContext(),"添加失败",Toast.LENGTH_LONG).show();
        }
    }

    public  void click2(View view) {
        SQLiteDatabase db = myOpenHelper.getWritableDatabase();
        //db.execSQL("delete from info where name = ?", new Object[] {"彭鹏"});
        //影响的行数
        int delete =db.delete("info","name=?",new String[]{"王五"});
        db.close();
        Toast.makeText(getApplicationContext(),"删除了"+delete+"行",Toast.LENGTH_SHORT).show();

    }

    public  void click3(View view) {
        SQLiteDatabase db = myOpenHelper.getWritableDatabase();
        //db.execSQL("update info set phone=? where name=?",new Object[]{"138888888","彭鹏"});
        ContentValues values =new ContentValues();
        values.put("phone","111");
       int update = db.update("info",values,"name=?",new String[]{"彭鹏"});
        Toast.makeText(getApplicationContext(),"更新了"+update+"行",Toast.LENGTH_SHORT).show();
    }
    //查找
    public  void click4(View view) {
        SQLiteDatabase db = myOpenHelper.getWritableDatabase();
        //having 过滤条件
       //Cursor cursor = db.query("info",new String[]{"phone"},"name=?",new String[]{"王五"},null,null,null);
        Cursor cursor = db.query("info",null,null,null,null,null,null);
        //Cursor cursor =db.rawQuery("select * from info",null);
        if(cursor!=null&&cursor.getCount()>0){
            while(cursor.moveToNext()){
                //列的索引
                String name=cursor.getString(1);
                String phone=cursor.getString(2);

                //把数据封装到javabean中
                Person person = new Person();
                person.setName(name);
                person.setPhone(phone);
                //把javabean对象加到集合
                lists.add(person);
            }
            //设置数据适配器
                lv.setAdapter(new Myadapter());
        }
    }
    //适配器
    private  class Myadapter extends BaseAdapter{

        @Override
        public int getCount() {
            return lists.size();
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
            System.out.println("------------------------++++++++++++++++++111");
            if(view ==null){
               //创建新的view对象
               view1=View.inflate(getApplicationContext(),R.layout.item,null);

           }else {
               view1=view;

           }
            //找到控件来显示数据
            TextView tv_name = (TextView) view1.findViewById(R.id.tv_name);
            TextView tv_phone = (TextView) view1.findViewById(R.id.tv_phone);
            System.out.println("------------------------++++++++++++++++++222");
            //如何显示数据
            Person person = lists.get(i);
            tv_name.setText(person.getName());
            tv_phone.setText(person.getPhone());
            System.out.println("------------------------++++++++++++++++++333");
            return view1;
        }
    }
}
