package com.example.c0115334.operator;

import android.app.TimePickerDialog;

import java.util.Calendar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void setUpTime(View v) {
        final TextView textView5 = (TextView) findViewById(R.id.textView5);
        final Calendar calendar = Calendar.getInstance();
        final int hour = calendar.get(Calendar.HOUR_OF_DAY);
        final int minute = calendar.get(Calendar.MINUTE);

        final TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        //受け取った時間と今の時間を比較して明日なのか今日なのかを判定
                        if (Integer.parseInt(String.valueOf(hourOfDay)+String.valueOf(minute)) >=
                                Integer.parseInt(String.valueOf(calendar.get(Calendar.HOUR_OF_DAY)) + String.valueOf(calendar.get(Calendar.MINUTE)))){
                            calendar.set(Calendar.DATE, calendar.get(Calendar.DATE)+1);
                        }
                        //受け取った値をセット
                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        calendar.set(Calendar.MINUTE, minute );
                        //0秒で起動
                        calendar.set(Calendar.SECOND, 0);
                        Intent intent = new Intent(getApplicationContext(), AlarmBroadcastReceiver.class);
                        intent.putExtra("intentId", 1);
                        PendingIntent pending = PendingIntent.getBroadcast(getApplicationContext(), 1, intent, 0);
                        // アラームをセットする
                        AlarmManager am = (AlarmManager) MainActivity.this.getSystemService(ALARM_SERVICE);
                        am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pending);
                        Toast.makeText(getApplicationContext(), "アラームをセットしました！", Toast.LENGTH_SHORT).show();
                        String setTime = String.valueOf(hourOfDay) + "時" + String.valueOf(minute) + "分";
                        textView5.setText(setTime);
                    }
                }, hour, minute, true);
        timePickerDialog.show();
    }

    public void setSleepTime(View v) {

        final Calendar calendar = Calendar.getInstance();
        final int hour = calendar.get(Calendar.HOUR_OF_DAY);
        final int minute = calendar.get(Calendar.MINUTE);

        final TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        Toast.makeText(MainActivity.this, hourOfDay + "時" + minute + "分", Toast.LENGTH_LONG).show();
                    }
                }, hour, minute, true);
        timePickerDialog.show();
    }

}
