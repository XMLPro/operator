package com.example.c0116278ba.myapplication;

/**
 * Created by c0116278ba on 2016/12/01.
 */

import android.content.Intent;
//import android.widget.ArrayAdapter;
//import android.database.sqlite.SQLiteDatabase;
import android.support.v4.view.MenuItemCompat;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private List<TodoItem> list = new ArrayList<TodoItem>();
    private ListView listview;
    private TodoAdapter todo;
    private Button btn_save;
    private EditText txt1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("activity", "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        todo = new TodoAdapter(this);
        Cursor c = todo.getAllList();
        if(c.moveToFirst()){
            do {
                TodoItem item = new TodoItem();
                item.set_id(c.getInt(c.getColumnIndex("_id")));
                item.setMemo(c.getString(c.getColumnIndex("memo")));
                list.add(item);
            } while(c.moveToNext());
        }

        // 紐付け
        listview = (ListView)findViewById(R.id.listView);

        // ArrayAdapterへ設定
        TodoListAdapter rowAdapater = new TodoListAdapter(this, 0, list);

        // リストビューへ設定
        listview.setAdapter(rowAdapater);

        btn_save = (Button)findViewById(R.id.button3);
        txt1 = (EditText)findViewById(R.id.editText);

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
    protected void onRestart() {

        Log.d("activity", "onRestart");

        list.clear();
        Cursor c = todo.getAllList();
        if(c.moveToFirst()){
            do {
                TodoItem item = new TodoItem();
                item.set_id(c.getInt(c.getColumnIndex("_id")));
                item.setMemo(c.getString(c.getColumnIndex("memo")));
                list.add(item);
            } while(c.moveToNext());
        }

        // 紐付け
        listview = (ListView)findViewById(R.id.listView);

        // ArrayAdapterへ設定
        TodoListAdapter rowAdapater = new TodoListAdapter(this, 0, list);

        // リストビューへ設定
        listview.setAdapter(rowAdapater);

        super.onRestart();
    }

}