package com.example.c0115334.operator;

/**
 * Created by c0116278ba on 2016/12/01.
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 */
public class TodoListAdapter extends ArrayAdapter<TodoItem> {
    private LayoutInflater layoutinflater;

    // コンストラクタ
    public TodoListAdapter(Context context, int textViewResourceId, List<TodoItem> objects){
        super(context, textViewResourceId, objects);
        layoutinflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 指定行のデータを取得
        TodoItem detail = (TodoItem)getItem(position);

        // nullの場合のみ再作成
        if(null == convertView){
            convertView = layoutinflater.inflate(R.layout.row, null);
        }

        // 行のデータを項目へ設定
        TextView text1 = (TextView)convertView.findViewById(R.id.textView);
        text1.setText(String.valueOf(detail.get_id()));

        TextView text2 = (TextView)convertView.findViewById(R.id.textView2);
        text2.setText(detail.getMemo());

        // 返却
        return convertView;
    }

}