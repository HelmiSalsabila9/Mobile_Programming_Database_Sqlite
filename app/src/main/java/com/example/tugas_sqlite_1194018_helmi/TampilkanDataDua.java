package com.example.tugas_sqlite_1194018_helmi;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class TampilkanDataDua extends AppCompatActivity implements AdapterView.OnItemClickListener{
    ListView listviewuser;
    DataBaseHelper dbhelper;
    LayoutInflater inflater;
    Button tambahdatadua, newData_user_dua;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampilkan_data_dua);
        dbhelper = new DataBaseHelper(this);
        listviewuser = (ListView)findViewById(R.id.list_data_user);
        listviewuser.setOnItemClickListener(this);
        tambahdatadua = (Button)findViewById(R.id.tambahdatadua);
        newData_user_dua = (Button)findViewById(R.id.newData_user_dua);
        tambahdatadua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TampilkanDataDua. this,
                        TambahDataDua.class);
                startActivity(intent);
            }
        });
        newData_user_dua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TampilkanDataDua. this,
                        MainActivity.class);
                startActivity(intent);
            }
        });
    }
    public void setListView(){
        Cursor cursor = dbhelper.getData();
        AdapterDua adapterDua = new AdapterDua(this,
                cursor, 1);
        listviewuser.setAdapter(adapterDua);
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int i, long x) {
        TextView getId = (TextView)view.findViewById(R.id.listID_user);
        final long id = Long.parseLong(getId.getText().toString());
        final Cursor cur = dbhelper.oneGetData(id);
        cur.moveToFirst();
        final AlertDialog.Builder builder = new
                AlertDialog.Builder(TampilkanDataDua.this); builder.setTitle("Menu : ");
        //Add a list
        String[] options = {"Edit Data","Lihat Data", "Hapus Data"};
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case 0:
                        inflater = getLayoutInflater();
                        Intent iddata = new Intent(TampilkanDataDua.this,
                                EditDataDua.class);
                        iddata.putExtra(DataBaseHelper.clm_id, id);
                        startActivity(iddata);
                }
                switch (which){
                    case 1:
                        inflater = getLayoutInflater();
                        Intent iddata = new Intent(TampilkanDataDua.this,
                                LihatDataDua.class);
                        iddata.putExtra(DataBaseHelper.user_id, id);
                        startActivity(iddata);
                }
                switch (which){
                    case 2:
                        AlertDialog.Builder builder1 = new
                                AlertDialog.Builder(TampilkanDataDua.this);
                        builder1.setMessage("Data ini akan dihapus.");
                        builder1.setCancelable(true);
                        builder1.setPositiveButton("Hapus", new
                                DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which)
                                    {
                                        dbhelper.deleteGetData(id);
                                        Toast.makeText(TampilkanDataDua.this, "Data Terhapus",
                                                Toast.LENGTH_SHORT).show();
                                        setListView();
                                    }
                                });
                        builder1.setNegativeButton("Batal", new
                                DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which)
                                    {
                                        dialog.cancel();
                                    }
                                });
                        AlertDialog alertDialog = builder1.create();
                        alertDialog.show();
                }
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    @Override
    protected void onResume() {
        super.onResume();
        setListView();
    }
    @Override
    public void onBackPressed() {
        finish();
    }
}
