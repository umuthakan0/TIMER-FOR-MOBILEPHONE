package com.demirgroup.timer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    TextView lblsecond;
    TextView lblsalise;
    TextView lblminute;
    Button button;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lblminute=findViewById(R.id.lblminute);
        lblsalise=findViewById(R.id.lblsalise);
        lblsecond=findViewById(R.id.lblsecond);
        button=findViewById(R.id.btnstart);
        sharedPreferences=this.getSharedPreferences("com.demirgroup.timer", Context.MODE_PRIVATE);
    }
    int loopbreak=0,second=0,minute=0,salise=0,procces=0;
    public void startorstop(View view){
        procces++;
        Timer timer=new Timer();
        if (procces==1){
            button.setText("Stop");
            loopbreak=0;
        }
        else if (procces==2)
        {
            procces=0;
            button.setText("Start");
            Toast.makeText(MainActivity.this,"Timer stoped",Toast.LENGTH_LONG).show();
            loopbreak=1;
        }
        TimerTask timerTask=new TimerTask() {
            @Override
            public void run() {
                salise++;
                lblsalise.setText(""+salise);
                if (salise==100){
                    second++;
                    lblsecond.setText(""+second);
                    salise=0;
                    if(second==59){
                        minute++;
                        lblminute.setText(""+minute);
                        second=0;
                    }
                }
                if(loopbreak==1)
                {
                    timer.cancel();
                }
            }
        };
        timer.schedule(timerTask,0,10);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sharedPreferences.edit().putInt("second",second).apply();
        sharedPreferences.edit().putInt("salise",salise).apply();
        sharedPreferences.edit().putInt("minute",minute).apply();
        Toast.makeText(MainActivity.this,"Time saved",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        second=sharedPreferences.getInt("second",0);
        minute=sharedPreferences.getInt("minute",0);
        salise=sharedPreferences.getInt("salise",0);
    }
}













