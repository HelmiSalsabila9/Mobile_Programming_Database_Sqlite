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

public class MainActivity extends AppCompatActivity {
    EditText editText_Nim, editText_NamaLengkap, editText_No_HP, editText_Alamat;
    RadioButton radioButton_L, radioButton_P;
    CheckBox checkBox;
    DataBaseHelper dbhelper;
    Spinner jurusan, angkatan;
    Button button,clear,tampilData;
    long id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbhelper = new DataBaseHelper(this);
        id = getIntent().getLongExtra(DataBaseHelper.clm_id, 0);
        radioButton_L = findViewById(R.id.radioButton_L);
        radioButton_P = findViewById(R.id.radioButton_P);
        editText_Nim = findViewById(R.id.editText_Nim);
        editText_NamaLengkap = findViewById(R.id.editText_NamaLengkap);
        editText_No_HP = findViewById(R.id.editText_No_HP);
        editText_Alamat = findViewById(R.id.editText_Alamat);
        tampilData = findViewById(R.id.tampildata);
        jurusan = findViewById(R.id.jurusan);
        angkatan = findViewById(R.id.angkatan);
        tampilData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity. this,
                        TampilkanData.class);
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
        checkBox = findViewById(R.id.checkBox);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Database();
            }
        });
    }
    private void Database(){
        String Nik = editText_Nim.getText().toString().trim();
        String Nama = editText_NamaLengkap.getText().toString().trim();
        String Nohp = editText_No_HP.getText().toString().trim();
        String Alamat = editText_Alamat.getText().toString().trim();
        String jur = jurusan.getSelectedItem().toString().trim();
        String angk = angkatan.getSelectedItem().toString().trim();
        String jk = (radioButton_L.isChecked()) ? "laki-laki" :
                (radioButton_P.isChecked()) ? "perempuan" : "-";
        String Status = checkBox.isChecked() ? "Mahasiswa" :
                "-";
        ContentValues values = new ContentValues();
        values.put(DataBaseHelper.clm_nim, Nik);
        values.put(DataBaseHelper.clm_namalkp, Nama);
        values.put(DataBaseHelper.clm_nohp, Nohp);
        values.put(DataBaseHelper.clm_alamat, Alamat);
        values.put(DataBaseHelper.clm_jurusan, jur);
        values.put(DataBaseHelper.clm_angkatan, angk);
        values.put(DataBaseHelper.clm_jk, jk);
        values.put(DataBaseHelper.clm_status, Status);
        if (Nik.equals("") || Nama.equals("") || Nohp.equals("")
                ||Alamat.equals("") || jur.equals("")|| angk.equals("")){
            Toast.makeText(MainActivity.this, "Data Tidak Boleh Kosong",
                    Toast.LENGTH_SHORT).show();
        }else {
            dbhelper.insertData(values);
            Toast.makeText(MainActivity.this, "Data Berhasil Tersimpan",
                    Toast.LENGTH_SHORT).show();
        }
    }
    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        String msg = "\n";
        switch (view.getId()) {
            case R.id.radioButton_L:
                if (checked)
                    msg = "You Clicked Laki - Laki";
                break;
            case R.id.radioButton_P:
                if (checked)
                    msg = "You Clicked Perempuan ";
                break;
        }
        Toast.makeText(getApplicationContext(), msg,
                Toast.LENGTH_SHORT).show();
    }
    private void CLEAR() {
        editText_Nim.setText("");
        editText_NamaLengkap.setText("");
        editText_No_HP.setText("");
        editText_Alamat.setText("");
        checkBox.setChecked(false);
        radioButton_L.setChecked(false);
        radioButton_P.setChecked(false);
    }
}