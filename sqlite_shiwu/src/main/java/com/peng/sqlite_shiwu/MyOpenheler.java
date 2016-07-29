package com.peng.sqlite_shiwu;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Peng on 2016/7/27.
 */
public class MyOpenheler extends SQLiteOpenHelper {
    public MyOpenheler(Context context) {
        super(context,"Acount.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table info(personid integer primary key autoincrement,name varchar(20)," +
                "phone  varchar(20),money varchar(20) NULL)");
        db.execSQL("insert into info(name,phone,money) values(?,?,?)", new Object[] {
                "彭鹏","188888787878","2000"});
        db.execSQL("insert into info(name,phone,money) values(?,?,?)", new Object[] {
                "张三","2453535353","4000"});
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
