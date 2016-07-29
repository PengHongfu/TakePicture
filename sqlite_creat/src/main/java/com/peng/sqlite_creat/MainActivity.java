package com.peng.sqlite_creat;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private  MyOpenHelper myOpenHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         myOpenHelper = new MyOpenHelper(getApplicationContext());

       //打开或创建数据库 如果是第一次就是创建
         SQLiteDatabase sqLiteDatabase = myOpenHelper.getWritableDatabase();

        //打开或创建数据库 如果是第一次就是创建 ,磁盘满了返回只读
        // SQLiteDatabase readableDatabase =myOpenHelper.getReadableDatabase();
    }
    public  void click1(View view) {
        //获取数据库对象
        SQLiteDatabase db = myOpenHelper.getWritableDatabase();
        db.execSQL("insert into info(name,phone) values(?,?)", new Object[] {
                "彭鹏","188888787878"});
        //关闭数据库
        db.close();
    }

    public  void click2(View view) {
        SQLiteDatabase db = myOpenHelper.getWritableDatabase();
        //db.execSQL("delete from info where name = ?", new Object[] {"彭鹏"});


    }

    public  void click3(View view) {
        SQLiteDatabase db = myOpenHelper.getWritableDatabase();
        db.execSQL("update info set phone=? where name=?",new Object[]{"138888888","彭鹏"});
    }
        //查找
    public  void click4(View view) {
        SQLiteDatabase db = myOpenHelper.getWritableDatabase();
        Cursor cursor =db.rawQuery("select * from info",null);
        if(cursor!=null&&cursor.getCount()>0){
            while(cursor.moveToNext()){
            //列的索引
            String name= cursor.getString(1);
            String phone=cursor.getString(2);
            System.out.println("name:"+name+"-----phone:"+phone);
            }

        }

    }
}
