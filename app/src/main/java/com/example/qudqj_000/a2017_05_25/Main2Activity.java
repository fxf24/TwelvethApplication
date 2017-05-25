package com.example.qudqj_000.a2017_05_25;

import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    TextView tv1;
    ProgressBar pb;
    myTask mt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tv1 = (TextView)findViewById(R.id.progressTv);
        pb = (ProgressBar)findViewById(R.id.progress);
    }

    public void onClick(View v){
        if(v.getId() == R.id.btn1){
            mt = new myTask();
            mt.execute(0);
        }
        if(v.getId() == R.id.btn2){
            mt.cancel(true);
        }
    }
    class myTask extends AsyncTask<Integer, Integer, Void>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pb.setProgress(0);
            tv1.setText("진행률 : 0%");
            tv1.setTextColor(Color.RED);
        }

        @Override
        protected Void doInBackground(Integer... params) {
            for(int i = 1; i<=100; i++){
                if(isCancelled() == true){
                    cancel(true);
                }
                try {
                    Thread.sleep(200);
                    publishProgress(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            pb.setProgress(values[0]);
            tv1.setText("진행률 : " + values[0] + "%");
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            pb.setProgress(100);
            tv1.setText("완료되었습니다.");
            tv1.setTextColor(Color.BLUE);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            tv1.setText("중지되었습니다.");
            tv1.setTextColor(Color.GRAY);
        }
    }
}
