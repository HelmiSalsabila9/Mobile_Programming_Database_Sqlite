package com.example.tugas_sqlite_1194018_helmi;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class AdapterDua extends CursorAdapter {
    private LayoutInflater layoutInflater;
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public AdapterDua(Context context, Cursor c, int flags) {
        super(context, c, flags);
        layoutInflater =
                (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        View v = layoutInflater.inflate(R.layout.row_getdata, viewGroup, false);
        MyHolder holder = new MyHolder();
        holder.ListID_user = (TextView)v.findViewById(R.id.listID_user);
        holder.ListNama_user = (TextView)v.findViewById(R.id.listNama_user);
        holder.ListEmail_user = (TextView)v.findViewById(R.id.ListEmail_user);
        v.setTag(holder);
        return v;
    }
    @SuppressLint("Range")
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        MyHolder holder = (MyHolder)view.getTag();

        holder.ListID_user.setText(cursor.getString(cursor.getColumnIndex(DataBaseHelper.user_id)));

        holder.ListNama_user.setText(cursor.getString(cursor.getColumnIndex(DataBaseHelper.user_nama)));

        holder.ListEmail_user.setText(cursor.getString(cursor.getColumnIndex(DataBaseHelper.user_email)));
    }
    class MyHolder{
        TextView ListID_user;
        TextView ListNama_user;
        TextView ListEmail_user;
    }
}

