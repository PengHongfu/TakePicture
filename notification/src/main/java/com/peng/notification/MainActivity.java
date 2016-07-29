package com.peng.notification;

import android.app.Activity;
import android.app.Notification;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends Activity {
    private EditText editText;
    private EditText editText2;
    private EditText editText3;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);
        editText2 = (EditText) findViewById(R.id.editText2);
        editText3 = (EditText) findViewById(R.id.editText3);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String shorttitle = editText.getText().toString();
                String title = editText2.getText().toString();
                String  content = editText3.getText().toString();
                //通知的标题,通知的简要标题,时间
                Notification notification =new Notification(android.R.drawable.stat_notify_chat,
                        shorttitle,System.currentTimeMillis());
                //通知的声音
                notification.defaults = Notification.DEFAULT_SOUND;
                //方法过时setLatestEventInfo
                //notification.setLatestEventInfo(this, title, content, contentIntent);
            }
        });
    }
}
