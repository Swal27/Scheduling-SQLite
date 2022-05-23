package com.example.myapplication;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.harrywhewell.scrolldatepicker.DayScrollDatePicker;
import com.harrywhewell.scrolldatepicker.OnDateSelectedListener;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    DayScrollDatePicker dayPicker;
    String selectedDate;
    Dialog dialog, dialog1;
    TextView tes;
    FloatingActionButton addbtn;
    ListView listData;
    private SQLiteDatabase db = null;
    private Cursor jadwalCursor = null;
    private DataJadwal datajadwal = null;
    ArrayList<List> arrayList;
    ListAdapter listAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dayPicker = findViewById(R.id.dayDatePicker);
        dialog = new Dialog(this);
        dialog1 = new Dialog(this);
        dayPicker.setStartDate(9,5,2022);
        addbtn = findViewById(R.id.adddata);
        tes = findViewById(R.id.coba);
        listData = (ListView)findViewById(R.id.listData);
        arrayList = new ArrayList<>();
        datajadwal = new DataJadwal(this);
        db = datajadwal.getWritableDatabase();
        datajadwal.createTable(db);
        dayPicker.getSelectedDate(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@Nullable Date date) {
                if(date != null) {
                    String selectedDate = date.toString();
                    tes.setText(selectedDate);
                    Toast.makeText(MainActivity.this, selectedDate, Toast.LENGTH_SHORT).show();

                    loadDataInListView(selectedDate);
                }
            }
        });
        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addjdwl = new Intent(MainActivity.this, AddJadwal.class);
                startActivity(addjdwl);
            }
        });
        listData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView idTv = (TextView) view.findViewById(R.id.idtv);
                String id = idTv.getText().toString();
                dialog.setContentView(R.layout.alertclick);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                TextView textReady = dialog.findViewById(R.id.tvready);
                Button btnEdit = dialog.findViewById(R.id.editbtn);
                Button btnHapus = dialog.findViewById(R.id.hapusbtn);
                dialog.show();

                btnHapus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        dialog1.setContentView(R.layout.deletealert);
                        dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                        Button noBtn = dialog1.findViewById(R.id.nobtn);
                        Button yesBtn = dialog1.findViewById(R.id.yesbtn);

                        dialog.dismiss();
                        dialog1.show();

                        yesBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                datajadwal.deleteData(id);
                                showToast();
                                dialog1.dismiss();
                                recreate();
                            }
                        });

                        noBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog1.dismiss();
                            }
                        });
                    }
                });
                btnEdit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(MainActivity.this,EditJadwal.class);
                        intent.putExtra("id",id);
                        startActivity(intent);
                    }
                });
            }
        });
    }

    private void showToast() {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.success, (ViewGroup) findViewById(R.id.success));

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER,0,0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);

        toast.show();
    }


    private void loadDataInListView(String date)
    {
        arrayList=datajadwal.getAllData(date);
        listAdapter = new ListAdapter(this,arrayList);
        listAdapter.notifyDataSetChanged();
        listData.setAdapter(listAdapter);
        
    }


}