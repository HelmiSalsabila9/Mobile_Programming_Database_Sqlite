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

public class EditData extends AppCompatActivity {
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
        setContentView(R.layout.activity_edit_data);
        dbhelper = new DataBaseHelper(this);
        id = getIntent().getLongExtra(DataBaseHelper.clm_id, 0);
        radioButton_L = findViewById(R.id.radioButton_L);
        radioButton_P = findViewById(R.id.radioButton_P);
        editText_Nim = findViewById(R.id.editText_Nim);
        editText_NamaLengkap = findViewById(R.id.editText_NamaLengkap);
        editText_No_HP = findViewById(R.id.editText_No_HP);
        editText_Alamat = findViewById(R.id.editText_Alamat);
        jurusan = findViewById(R.id.jurusan);
        angkatan = findViewById(R.id.angkatan);
        tampilData = findViewById(R.id.back);
        tampilData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditData. this, TampilkanData.class);
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
        checkBox = findViewById(R.id.checkBox);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Database(); // menyimpang data yang baru
            }
        });
        getData(); //fungsinya yaitu mendapatkan data dari tampilan data yang maudi edit
    }
    private void Database(){
        String Nim = editText_Nim.getText().toString().trim();
        String Nama = editText_NamaLengkap.getText().toString().trim();
        String Nohp = editText_No_HP.getText().toString().trim();
        String Alamat = editText_Alamat.getText().toString().trim();
        String jur = jurusan.getSelectedItem().toString().trim();
        String angk = angkatan.getSelectedItem().toString().trim();
        String jk = (radioButton_L.isChecked()) ? "laki-laki" :
                (radioButton_P.isChecked()) ? "perempuan" : "-";
        String status = checkBox.isChecked() ? "Mahasiswa" :
                "-";
        ContentValues values = new ContentValues();
        values.put(DataBaseHelper.clm_nim, Nim);
        values.put(DataBaseHelper.clm_namalkp, Nama);
        values.put(DataBaseHelper.clm_nohp, Nohp);
        values.put(DataBaseHelper.clm_alamat, Alamat);
        values.put(DataBaseHelper.clm_jurusan, jur);
        values.put(DataBaseHelper.clm_angkatan, angk);
        values.put(DataBaseHelper.clm_status, status);
        values.put(DataBaseHelper.clm_jk, jk);
        if (Nim.equals("") || Nama.equals("") || Nohp.equals("") ||
                Alamat.equals("")) {
            Toast.makeText(EditData.this, "Data Tidak Boleh Kosong",
                    Toast.LENGTH_SHORT).show();
        } else {
            dbhelper.updateData(values, id);
            Toast.makeText(EditData.this, "Data Berhasil Tersimpan",
                    Toast.LENGTH_SHORT).show();
            finish();
        }
    }
    private void getData(){
        Cursor cursor = dbhelper.oneData(id);
        if(cursor.moveToFirst()){
            @SuppressLint("Range") String Nim =
                    cursor.getString(cursor.getColumnIndex(DataBaseHelper.clm_nim));
            @SuppressLint("Range") String Nama =
                    cursor.getString(cursor.getColumnIndex(DataBaseHelper.clm_namalkp));
            @SuppressLint("Range") String Nohp =
                    cursor.getString(cursor.getColumnIndex(DataBaseHelper.clm_nohp));
            @SuppressLint("Range") String Alamat =
                    cursor.getString(cursor.getColumnIndex(DataBaseHelper.clm_alamat));
            @SuppressLint("Range") String jur =
                    cursor.getString(cursor.getColumnIndex(DataBaseHelper.clm_jurusan));
            @SuppressLint("Range") String angk =
                    cursor.getString(cursor.getColumnIndex(DataBaseHelper.clm_angkatan));
            @SuppressLint("Range") String JK =
                    cursor.getString(cursor.getColumnIndex(DataBaseHelper.clm_jk));
            @SuppressLint("Range") String status =
                    cursor.getString(cursor.getColumnIndex(DataBaseHelper.clm_status));
            editText_Nim.setText(Nim);
            editText_NamaLengkap.setText(Nama);
            editText_No_HP.setText(Nohp);
            editText_Alamat.setText(Alamat);
            if (JK.equals("laki-laki")){
                radioButton_L.setChecked(true);
            }else if (JK.equals("perempuan")) {
                radioButton_P.setChecked(true);
            }
            if (status.equals("Mahasiswa")){
                checkBox.setChecked(true);
            }
            if (jur.equals("Teknik Informatika")){
                jurusan.setSelection(0);
            }else if(jur.equals("Sistem Informatika")){
                jurusan.setSelection(1);
            } else if(jur.equals("Farmasi")){
                jurusan.setSelection(2);
            }else if(jur.equals("Ilmu Hukum")){
                jurusan.setSelection(3);
            }else if(jur.equals("Sastra dan Bahasa Asing")){
                jurusan.setSelection(4);
            }else if(jur.equals("Ilmu Kelautan dan Perikanan")){
                jurusan.setSelection(5);
            }else if(jur.equals("Ilmu Sosial dan Ilmu Politik")){
                jurusan.setSelection(6);
            }else if(jur.equals("Kesehatan Masyarakat")){
                jurusan.setSelection(7);
            }else if(jur.equals("Kedokteran")){
                jurusan.setSelection(8);
            }else if(jur.equals("Kedokteran Gigi")){
                jurusan.setSelection(9);
            }else if(jur.equals("Pendidikan Dokter Gigi")){
                jurusan.setSelection(10);
            }else if(jur.equals("Kehutanan")){
                jurusan.setSelection(11);
            }else if(jur.equals("Peternakan")){
                jurusan.setSelection(12);
            }
            if (angk.equals("2010")){
                angkatan.setSelection(0);
            }else if(angk.equals("2011")){
                angkatan.setSelection(1);
            } else if(angk.equals("2012")){
                angkatan.setSelection(2);
            }else if(angk.equals("2013")){
                angkatan.setSelection(3);
            }else if(angk.equals("2014")){
                angkatan.setSelection(4);
            }else if(angk.equals("2015")){
                angkatan.setSelection(5);
            }else if(angk.equals("2016")){
                angkatan.setSelection(6);
            }else if(angk.equals("2017")){
                angkatan.setSelection(7);
            }else if(angk.equals("2018")){
                angkatan.setSelection(8);
            }else if(angk.equals("2019")){
                angkatan.setSelection(9);
            }else if(angk.equals("2020")){
                angkatan.setSelection(10);
            }else if(angk.equals("2021")){
                angkatan.setSelection(11);
            }else if(angk.equals("2022")){
                angkatan.setSelection(12);
            }else if(angk.equals("2023")){
                angkatan.setSelection(13);
            }else if(angk.equals("2024")){
                angkatan.setSelection(14);
            }else if(angk.equals("2025")){
                angkatan.setSelection(15);
            }else if(angk.equals("2026")){
                angkatan.setSelection(16);
            }else if(angk.equals("2027")){
                angkatan.setSelection(17);
            }else if(angk.equals("2028")){
                angkatan.setSelection(18);
            }else if(angk.equals("2029")){
                angkatan.setSelection(19);
            }else if(angk.equals("2030")){
                angkatan.setSelection(20);
            }
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