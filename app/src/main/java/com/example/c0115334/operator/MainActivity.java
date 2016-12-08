package com.example.c0115334.operator;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.app.TimePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.util.Log;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private List<TodoItem> list = new ArrayList<TodoItem>();
    private ListView listview;
    private TodoAdapter todo;
    private Button btn_save;
    private EditText txt1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Button betTimeSet = (Button)findViewById(R.id.button1);
        betTimeSet.setOnClickListener(this);*/
        todo = new TodoAdapter(this);
        Cursor c = todo.getAllList();
        if (c.moveToFirst()) {
            do {
                TodoItem item = new TodoItem();
                item.set_id(c.getInt(c.getColumnIndex("_id")));
                item.setMemo(c.getString(c.getColumnIndex("memo")));
                list.add(item);
            } while (c.moveToNext());
        }

        // 紐付け
        listview = (ListView) findViewById(R.id.listView);

        // ArrayAdapterへ設定
        TodoListAdapter rowAdapater = new TodoListAdapter(this, 0, list);

        // リストビューへ設定
        listview.setAdapter(rowAdapater);

        btn_save = (Button) findViewById(R.id.button3);
        txt1 = (EditText) findViewById(R.id.editText);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 値を保存する
                todo.insert(txt1.getText().toString());

                // 画面を閉じる
                //finish();
            }
        });

        // todo
        todo = new TodoAdapter(this);
    }

    @Override
    public void onClick(View v) {
        /*Intent bootIntent = new Intent(MainActivity.this, BedTimeReceiver.class);
        PendingIntent alarmIntent = PendingIntent.getBroadcast(MainActivity.this, 0, bootIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        AlarmManager alarm = (AlarmManager)getSystemService(Context.ALARM_SERVICE);


        Calendar startTime = Calendar.getInstance();
        startTime.set(Calendar.HOUR_OF_DAY, 15);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.SECOND, 0);
        long alarmStartTime = startTime.getTimeInMillis();

        alarm.set(AlarmManager.RTC_WAKEUP, alarmStartTime, alarmIntent);
        Toast.makeText(MainActivity.this, "通知をセットしました！", Toast.LENGTH_SHORT).show();
        */
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
                        if (Integer.parseInt(String.valueOf(hourOfDay) + String.valueOf(minute)) >=
                                Integer.parseInt(String.valueOf(calendar.get(Calendar.HOUR_OF_DAY)) + String.valueOf(calendar.get(Calendar.MINUTE)))) {
                            calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + 1);
                        }
                        //受け取った値をセット
                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        calendar.set(Calendar.MINUTE, minute);
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
        final TextView textView4 = (TextView) findViewById(R.id.textView4);

        final Calendar calendar = Calendar.getInstance();
        final int hour = calendar.get(Calendar.HOUR_OF_DAY);
        final int minute = calendar.get(Calendar.MINUTE);

        final Calendar startTime = Calendar.getInstance();

        final TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        //Toast.makeText(MainActivity.this, hourOfDay+"時"+minute+"分", Toast.LENGTH_LONG).show();
                        startTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        startTime.set(Calendar.MINUTE, minute);
                        startTime.set(Calendar.SECOND, 0);
                        Intent bootIntent = new Intent(MainActivity.this, BedTimeReceiver.class);
                        PendingIntent alarmIntent = PendingIntent.getBroadcast(MainActivity.this, 0, bootIntent, PendingIntent.FLAG_CANCEL_CURRENT);
                        AlarmManager alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                        long alarmStartTime = startTime.getTimeInMillis();
                        alarm.set(AlarmManager.RTC_WAKEUP, alarmStartTime, alarmIntent);
                        Toast.makeText(MainActivity.this, "通知をセットしました！", Toast.LENGTH_SHORT).show();
                        String setTime = String.valueOf(hourOfDay) + "時" + String.valueOf(minute) + "分";
                        textView4.setText(setTime);

                    }
                }, hour, minute, true);
        timePickerDialog.show();

    }

    @Override
    protected void onRestart() {

        Log.d("activity", "onRestart");

        list.clear();
        Cursor c = todo.getAllList();
        if (c.moveToFirst()) {
            do {
                TodoItem item = new TodoItem();
                item.set_id(c.getInt(c.getColumnIndex("_id")));
                item.setMemo(c.getString(c.getColumnIndex("memo")));
                list.add(item);
            } while (c.moveToNext());
        }

        // 紐付け
        listview = (ListView) findViewById(R.id.listView);

        // ArrayAdapterへ設定
        TodoListAdapter rowAdapater = new TodoListAdapter(this, 0, list);

        // リストビューへ設定
        listview.setAdapter(rowAdapater);

        super.onRestart();
    }

}
