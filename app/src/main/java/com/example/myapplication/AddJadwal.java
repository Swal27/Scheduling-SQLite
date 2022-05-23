package com.example.myapplication;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.harrywhewell.scrolldatepicker.DayScrollDatePicker;
import com.harrywhewell.scrolldatepicker.OnDateSelectedListener;

import java.util.Date;

public class AddJadwal extends AppCompatActivity {
    DayScrollDatePicker dayPicker;
    String selectedDate;
    TextInputLayout jamulai, jamakhir, matkul, dosen, lab, jurusan, asisten;
    TimePickerDialog timePickerDialog;
    TextView tgl;
    private SQLiteDatabase db = null;
    private DataJadwal datajadwal = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addjadwal);
        dayPicker = findViewById(R.id.dayDatePicker);
        jamulai = findViewById(R.id.jamulai);
        jamakhir = findViewById(R.id.jamakhir);
        matkul = findViewById(R.id.matkul);
        dosen = findViewById(R.id.dosen);
        lab = findViewById(R.id.lab);
        tgl = findViewById(R.id.tgl);
        asisten = findViewById(R.id.asisten);
        datajadwal = new DataJadwal(this);
        db = datajadwal.getWritableDatabase();
        datajadwal.createTable(db);
        jurusan = findViewById(R.id.jurusan);
        dayPicker.setStartDate(9,5,2022);
        dayPicker.getSelectedDate(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@Nullable Date date) {
                if(date != null) {
                    String selectedDate = date.toString();
                    tgl.setText(selectedDate);
                    Toast.makeText(AddJadwal.this, selectedDate, Toast.LENGTH_SHORT).show();
                }
            }
        });

        jamulai.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timePickerDialog = new TimePickerDialog(AddJadwal.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                    jamulai.getEditText().setText(String.format("%02d:%02d",hour,minute));
                    }
                },0,0,false);
                timePickerDialog.show();
            }
        });

        jamakhir.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timePickerDialog = new TimePickerDialog(AddJadwal.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                        jamakhir.getEditText().setText(String.format("%02d:%02d",hour,minute));
                    }
                },0,0,false);
                timePickerDialog.show();
            }
        });
    }
    public void submit(View view) {
        db.execSQL("insert into jadwal(tanggal, matkul, jamulai, jamakhir, dosen, asisten, lab, jurusan)" + "values('"+ tgl.getText().toString() +"',"+"'"+matkul.getEditText().getText().toString()+"',"+"'"+jamulai.getEditText().getText().toString()+"',"+"'"+jamakhir.getEditText().getText().toString()+"',"+"'"+dosen.getEditText().getText().toString()+"',"+"'"+asisten.getEditText().getText().toString()+"',"+"'"+lab.getEditText().getText().toString()+"',"+"'"+jurusan.getEditText().getText().toString()+"')");
        showToast();
        Intent intent = new Intent().setClass(AddJadwal.this,MainActivity.class);
        startActivity(intent);
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
}