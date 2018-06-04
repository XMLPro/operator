package com.example.c0115334.operator;

/**
 * Created by c0116278ba on 2016/12/01.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 */
public class TodoAdapter {

    private SQLiteDatabase db;
    static private DBOpenHelper helper;
    ContentValues values = new ContentValues();

    // コンストラクタ
    public TodoAdapter(Context context){
        helper = new DBOpenHelper(context);
        db = helper.getWritableDatabase();
    }

    // リストを取得
    public Cursor getAllList(){
        return db.query(DBOpenHelper.TABLE_NAME, null, null, null, null, null, null);
    }

    // 追加
    public void insert(String memo){
        values.put("memo", memo);
        db.insertOrThrow(DBOpenHelper.TABLE_NAME, null, values);
    }

    //削除
    public  void delete(){
        //values.clear();
        //db.insertOrThrow(DBOpenHelper.TABLE_NAME, null, values);
    }

}