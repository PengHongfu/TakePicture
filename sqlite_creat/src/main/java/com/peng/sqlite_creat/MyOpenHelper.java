package com.peng.sqlite_creat;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Peng on 2016/7/26.
 */
public class MyOpenHelper  extends SQLiteOpenHelper{

    /**
     *
     * @param context 上下文
     * name 数据库的名字
     *factory 目的创建cursor对象
     */
    public MyOpenHelper(Context context) {
        super(context, "peng.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 在数据库第一次被创建时被调用的
        db.execSQL("create table info(_id integer primary key autoincrement," +
                "name varchar(20),phone  varchar(20) NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //数据库版本升级用
        //db.execSQL("ALTER TABLE person ADD amount integer NULL ");
    }
}
