package com.example.c0115334.operator;

import android.app.TimePickerDialog;
import java.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity {

    private static final int bid1 = 1;
    private static final int bid2 = 2;

    private Button button1, button2, button3;
    private TextView textView;
    private int year, month, date, hour, minute, second, msecond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 10秒で繰り返しアラーム
        button1 = (Button)this.findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(System.currentTimeMillis());
                // 5秒後に設定
                calendar.add(Calendar.SECOND, 5);

                Intent intent = new Intent(getApplicationContext(), AlarmBroadcastReceiver.class);
                intent.putExtra("intentId", 1);
                // PendingIntentが同じ物の場合は上書きされてしまうので requestCode で区別する
                PendingIntent pending = PendingIntent.getBroadcast(getApplicationContext(), bid1, intent, 0);

                // アラームをセットする
                AlarmManager am = (AlarmManager) MainActivity.this.getSystemService(ALARM_SERVICE);
                // 約10秒で 繰り返し
                am.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 10000, pending);

                // トーストで設定されたことをを表示
                Toast.makeText(getApplicationContext(), "ALARM 1", Toast.LENGTH_SHORT).show();

                // 無理やりですが、アプリを一旦終了します。この方法はバックグラウンドに移行させるための方便で推奨ではありません
                close();
            }
        });

        // アラームの取り消し
        button2 = (Button)this.findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent indent = new Intent(getApplicationContext(), AlarmBroadcastReceiver.class);
                PendingIntent pending = PendingIntent.getBroadcast(getApplicationContext(), bid1, indent, 0);

                // アラームを解除する
                AlarmManager am = (AlarmManager)MainActivity.this.getSystemService(ALARM_SERVICE);
                am.cancel(pending);
                Toast.makeText(getApplicationContext(), "Cancel ALARM 1", Toast.LENGTH_SHORT).show();

            }
        });

        textView = (TextView)findViewById(R.id.text_view);
        // 協定世界時 (UTC)です適宜設定してください
        year = 2016;
        month = 11;// 4=>5月
        date = 3;
        hour = 2;
        minute = 28;
        second = 10;
        msecond = 0;

        // 日時を指定したアラーム
        button3 = (Button)this.findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar2 = Calendar.getInstance();
                // 過去の時間は即実行されます
                calendar2.set(Calendar.YEAR, year);
                calendar2.set(Calendar.MONTH, month);
                calendar2.set(Calendar.DATE, date);
                calendar2.set(Calendar.HOUR_OF_DAY, hour);
                calendar2.set(Calendar.MINUTE, minute );
                calendar2.set(Calendar.SECOND, second);
                calendar2.set(Calendar.MILLISECOND, msecond);

                Intent intent = new Intent(getApplicationContext(), AlarmBroadcastReceiver.class);
                intent.putExtra("intentId", 2);
                PendingIntent pending = PendingIntent.getBroadcast(getApplicationContext(), bid2, intent, 0);

                // アラームをセットする
                AlarmManager am = (AlarmManager)MainActivity.this.getSystemService(ALARM_SERVICE);
                am.set(AlarmManager.RTC_WAKEUP, calendar2.getTimeInMillis(), pending);
                Toast.makeText(getApplicationContext(), "ALARM 2", Toast.LENGTH_SHORT).show();

                String setTime = "設定時間(UTC)："+year+"/"+(month+1)+"/"+date+" "+hour+":"+minute+":"+second+"."+msecond;
                textView.setText(setTime);
            }
        });
    }

    private void close(){
        finish();
    }
    public void setUpTime(View v){

        final Calendar calendar = Calendar.getInstance();
        final int hour = calendar.get(Calendar.HOUR_OF_DAY);
        final int minute = calendar.get(Calendar.MINUTE);

        final TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        Toast.makeText(MainActivity.this, hourOfDay+"時"+minute+"分", Toast.LENGTH_LONG).show();
                    }
                }, hour, minute, true);
        timePickerDialog.show();
    }

    public void setSleepTime(View v){

        final Calendar calendar = Calendar.getInstance();
        final int hour = calendar.get(Calendar.HOUR_OF_DAY);
        final int minute = calendar.get(Calendar.MINUTE);

        final TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        Toast.makeText(MainActivity.this, hourOfDay+"時"+minute+"分", Toast.LENGTH_LONG).show();
                    }
                }, hour, minute, true);
        timePickerDialog.show();
    }

}
