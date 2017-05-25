package com.example.qudqj_000.a2017_05_25;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText et1, et2;
    MyThread mt = new MyThread();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mt.subHandler.getLooper().quit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et1 = (EditText) findViewById(R.id.et1);
        et2 = (EditText) findViewById(R.id.et2);

        mt.setDaemon(true);
        mt.start();
    }

    public void onClick(View v){
        if(v.getId() == R.id.button1){
//            MyThread mt = new MyThread();
//            mt.setDaemon(true);
//            mt.start();
            Message msg = Message.obtain();
            msg.obj = et1.getText().toString();
            mt.subHandler.sendMessageDelayed(msg,3000);
        }
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            String name = (String)msg.obj;
            et2.setText(name);
        }
    };

    class MyThread extends Thread{
        SubHandler subHandler = new SubHandler();
        @Override
        public void run() {
            Looper.prepare();
            Looper.loop();
        }

    }

    class  SubHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            String name = (String)msg.obj;
            name = "안녕하세요 " + name;

            Message msg1 = Message.obtain();
            msg1.obj = name;
            handler.sendMessage(msg1);
        }
    }

}
