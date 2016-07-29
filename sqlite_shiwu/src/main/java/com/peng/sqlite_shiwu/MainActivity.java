package com.peng.sqlite_shiwu;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private MyOpenheler myOpenheler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         myOpenheler =new MyOpenheler(getApplicationContext());
        myOpenheler.getReadableDatabase();
    }
    //点击进行转账
    public void click(View view) {
        SQLiteDatabase db =myOpenheler.getReadableDatabase();
        //使用事物进行转账
        db.beginTransaction();
        try {
            //实现转账逻辑

            db.execSQL("update info set money=money-100 where name=?",new Object[]{"彭鹏"});
            db.execSQL("update info set money=money+100 where name=?",new Object[]{"张三"});
            //逻辑是同时成功,才执行事物,否则回滚
            db.setTransactionSuccessful();

        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(),"服务器忙,请稍候再试",Toast.LENGTH_LONG).show();

        }finally {
            db.endTransaction();//关闭事物
        }



    }
}
