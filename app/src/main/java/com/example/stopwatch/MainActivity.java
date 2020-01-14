package com.example.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private boolean running;
    private int seconds = 0;
    private boolean wasrunning;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState!=null)
        {
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
        }
        running();
    }

    public void start(View view) {
        running = true;
    }

    public void stop(View view) {
        running = false;
    }

    public void reset(View view) {
        running = false;
        seconds = 0;
    }

    private void running() {
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            public void run() {
                int hours;
                hours = (seconds) / 3600;
                int minutes;
                minutes = (seconds) / 60;
                int second;
                second = seconds % 60;
                TextView a = (TextView) findViewById(R.id.text1);
                a.setText(hours + ":" + minutes + ":" + second);
                if (running == true) {
                    seconds++;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }
    @Override
    public   void onSaveInstanceState(Bundle savedInstanceState)
    {

        savedInstanceState.putInt("seconds",seconds);
        savedInstanceState.putBoolean("running",running);
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        wasrunning=running;
        running=false;
    }
    @Override
    protected void onResume()
    {
        super.onResume();
        running=wasrunning;
    }
}
