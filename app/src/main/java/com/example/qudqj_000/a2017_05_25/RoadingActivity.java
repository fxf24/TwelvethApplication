package com.example.qudqj_000.a2017_05_25;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class RoadingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roading);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(RoadingActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3000);
    }

    Handler handler = new Handler();
}
