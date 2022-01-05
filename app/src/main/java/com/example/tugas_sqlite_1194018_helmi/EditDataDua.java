package com.example.tugas_sqlite_1194018_helmi;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class EditDataDua extends AppCompatActivity {
    EditText editNamaUser, editEmailUser;
    DataBaseHelper dbhelper;
    Button button,clear,tampilData;
    long id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data_dua);
        dbhelper = new DataBaseHelper(this);
        id = getIntent().getLongExtra(DataBaseHelper.user_id, 0);
        editNamaUser = findViewById(R.id.editNamaUser);
        editEmailUser = findViewById(R.id.editEmailUser);
        tampilData = findViewById(R.id.back);
        tampilData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditDataDua. this, TampilkanDataDua.class);
                startActivity(intent);
            }
        });
        clear = findViewById(R.id.clear);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CLEAR();//membersihkan data
            }
        });
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Database(); // menyimpan data yang baru
            }
        });
        getData(); //fungsinya yaitu mendapatkan data dari tampilan data yang maudi edit
    }
    private void Database(){
        String Nama = editNamaUser.getText().toString().trim();
        String Email = editEmailUser.getText().toString().trim();

        ContentValues values = new ContentValues();
        values.put(DataBaseHelper.user_nama, Nama);
        values.put(DataBaseHelper.user_email, Email);

        if (Nama.equals("") || Email.equals("")) {
            Toast.makeText(EditDataDua.this, "Data Tidak Boleh Kosong",
                    Toast.LENGTH_SHORT).show();
        } else {
            dbhelper.updateData(values, id);
            Toast.makeText(EditDataDua.this, "Data Berhasil Tersimpan",
                    Toast.LENGTH_SHORT).show();
            finish();
        }
    }
    private void getData(){
        Cursor cursor = dbhelper.oneGetData(id);
        if(cursor.moveToFirst()){
            @SuppressLint("Range") String Nama =
                    cursor.getString(cursor.getColumnIndex(DataBaseHelper.user_nama));
            @SuppressLint("Range") String Email =
                    cursor.getString(cursor.getColumnIndex(DataBaseHelper.user_email));

            editNamaUser.setText(Nama);
            editEmailUser.setText(Email);
        }
    }

    private void CLEAR() {
        editNamaUser.setText("");
        editEmailUser.setText("");
    }
}