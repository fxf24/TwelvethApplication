package com.example.qudqj_000.a2017_05_25;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    TextView time;
    EditText et1;
    ImageView iv1;
    myTask mt;
    int count, seconds, index;
    static int food[] = {R.drawable.chicken, R.drawable.hamburger, R.drawable.hansik,
            R.drawable.kalguksu, R.drawable.pizza,
            R.drawable.spaghetti, R.drawable.ssalguksu};
    static String foodName[] = {"치킨", "햄버거",
            "한식", "칼국수", "피자",
            "스파게티", "쌀국수"};
    boolean startCheck = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        time = (TextView)findViewById(R.id.time_count);
        et1 = (EditText)findViewById(R.id.seconds);
        iv1=(ImageView)findViewById(R.id.food);
    }

    public void onClick(View v){
        if(v.getId() == R.id.to_The_First){
            iv1.setImageResource(R.drawable.start_button);
            time.setVisibility(View.GONE);
            startCheck = true;
        }
        if(v.getId() == R.id.food){
            if(startCheck) {
                mt = new myTask();
                mt.execute(0);
                startCheck = false;
            }
            else{
                mt.cancel(true);
            }
        }
    }

    class myTask extends AsyncTask<Integer, Integer, Void>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            iv1.setImageResource(food[0]);
            index = 0;
            count = Integer.parseInt(et1.getText().toString());
            seconds = 0;
            time.setVisibility(View.VISIBLE);
            time.setText("시작부터 " + seconds + "초");
        }

        @Override
        protected Void doInBackground(Integer... params) {
            while(isCancelled()==false){
                seconds++;
                try {
                    Thread.sleep(1000);
                    if(seconds%count == 0){
                        if(index>=6) {
                            index = 0;
                        }
                        publishProgress(seconds, ++index);
                    }
                    else {
                        publishProgress(seconds, index);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            cancel(true);
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            time.setText("시작부터 " + values[0] + "초");
            iv1.setImageResource(food[values[1]]);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            time.setText(foodName[index] + "선택" +"(" + (seconds-1)+" 초)");
        }
    }
}
