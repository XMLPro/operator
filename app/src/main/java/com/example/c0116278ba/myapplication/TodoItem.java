package com.example.c0116278ba.myapplication;

/**
 * Created by c0116278ba on 2016/12/01.
 */

public class TodoItem {
    private int _id;
    private String memo;

    public void set_id(int _id) {
        this._id = _id;
    }

    public int get_id(){
        return this._id;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getMemo(){
        return this.memo;
    }
}
