package com.example.tugas_sqlite_1194018_helmi;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class TambahDataDua extends AppCompatActivity {
    EditText editText_Nama_user, editText_Email;
    DataBaseHelper dbhelper;
    Button button,clear,tampilDataDua;
    long id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_data_dua);
        dbhelper = new DataBaseHelper(this);
        id = getIntent().getLongExtra(DataBaseHelper.user_id, 0);
        editText_Nama_user = findViewById(R.id.editText_Nama_user);
        editText_Email = findViewById(R.id.editText_Email);
        tampilDataDua = findViewById(R.id.tampildatadua);
        tampilDataDua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TambahDataDua. this,
                        TampilkanDataDua.class);
                startActivity(intent);
            }
        });
        clear = findViewById(R.id.clear);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CLEAR();
            }
        });
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Database();
            }
        });
    }
    private void Database(){
        String Nama = editText_Nama_user.getText().toString().trim();
        String Email = editText_Email.getText().toString().trim();

        ContentValues values = new ContentValues();
        values.put(DataBaseHelper.user_nama, Nama);
        values.put(DataBaseHelper.user_email, Email);

        if (Nama.equals("") || Email.equals("")){
            Toast.makeText(TambahDataDua.this, "Data Tidak Boleh Kosong",
                    Toast.LENGTH_SHORT).show();
        }else {
            dbhelper.insertGetData(values);
            Toast.makeText(TambahDataDua.this, "Data Berhasil Tersimpan",
                    Toast.LENGTH_SHORT).show();
        }
    }
    private void CLEAR() {
        editText_Nama_user.setText("");
        editText_Email.setText("");
    }
}