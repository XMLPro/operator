package com.example.c0115334.operator;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button betTimeSet = (Button)findViewById(R.id.betTimeSet);
        betTimeSet.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent bootIntent = new Intent(MainActivity.this, BedTimeReceiver.class);
        PendingIntent alarmIntent = PendingIntent.getBroadcast(MainActivity.this, 0, bootIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        AlarmManager alarm = (AlarmManager)getSystemService(Context.ALARM_SERVICE);


        Calendar startTime = Calendar.getInstance();
        startTime.set(Calendar.HOUR_OF_DAY, 15);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.SECOND, 0);
        long alarmStartTime = startTime.getTimeInMillis();

        alarm.set(AlarmManager.RTC_WAKEUP, alarmStartTime, alarmIntent);
        Toast.makeText(MainActivity.this, "通知をセットしました！", Toast.LENGTH_SHORT).show();


    }
}
