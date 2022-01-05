package com.example.tugas_sqlite_1194018_helmi;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class LihatDataDua extends AppCompatActivity {
    TextView Nama, Email;
    DataBaseHelper dbhelper;
    Button tampilData;
    long id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_data_dua);
        dbhelper = new DataBaseHelper(this);
        id = getIntent().getLongExtra(DataBaseHelper.user_id, 0);
        Nama = findViewById(R.id.nama);
        Email = findViewById(R.id.email);
        tampilData = findViewById(R.id.tampildata);
        tampilData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LihatDataDua. this, TampilkanDataDua.class);
                startActivity(intent);
            }
        });
        getData(); //fungsinya yaitu mendapatkan data dari tampilan data yang diedit
    }
    private void getData(){
        Cursor cursor = dbhelper.oneGetData(id);
        if(cursor.moveToFirst()){
            @SuppressLint("Range") String nama =
                    cursor.getString(cursor.getColumnIndex(DataBaseHelper.user_nama));
            @SuppressLint("Range") String email =
                    cursor.getString(cursor.getColumnIndex(DataBaseHelper.user_email));

            Nama.setText(nama);
            Email.setText(email);
        }
    }
}