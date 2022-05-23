package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.harrywhewell.scrolldatepicker.DayScrollDatePicker;
import com.harrywhewell.scrolldatepicker.OnDateSelectedListener;

import java.util.Date;

public class EditJadwal extends AppCompatActivity {

    DayScrollDatePicker dayPicker;
    String selectedDate,id;
    TextInputLayout jamulai, jamakhir, matkul, dosen, lab, jurusan, asisten;
    TimePickerDialog timePickerDialog;
    TextView tgl;
    Button subEdit;
    private SQLiteDatabase db = null;
    private DataJadwal datajadwal = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editjadwal);
        dayPicker = findViewById(R.id.dayDatePicker);
        jamulai = findViewById(R.id.jamulai);
        jamakhir = findViewById(R.id.jamakhir);
        matkul = findViewById(R.id.matkul);
        dosen = findViewById(R.id.dosen);
        lab = findViewById(R.id.lab);
        tgl = findViewById(R.id.tgl);
        asisten = findViewById(R.id.asisten);
        subEdit = findViewById(R.id.submitEdit);
        datajadwal = new DataJadwal(this);
        db = datajadwal.getWritableDatabase();
        datajadwal.createTable(db);
        jurusan = findViewById(R.id.jurusan);
        dayPicker.setStartDate(9,5,2022);
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        dayPicker.getSelectedDate(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@Nullable Date date) {
                if(date != null) {
                    String selectedDate = date.toString();
                    tgl.setText(selectedDate);
                    Toast.makeText(EditJadwal.this, selectedDate, Toast.LENGTH_SHORT).show();
                }
            }
        });

        jamulai.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timePickerDialog = new TimePickerDialog(EditJadwal.this, new TimePickerDialog.OnTimeSetListener() {
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
                timePickerDialog = new TimePickerDialog(EditJadwal.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                        jamakhir.getEditText().setText(String.format("%02d:%02d",hour,minute));
                    }
                },0,0,false);
                timePickerDialog.show();
            }
        });
        subEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.execSQL("UPDATE jadwal set tanggal = '" + tgl.getText().toString() + "',matkul = " + "'" + matkul.getEditText().getText().toString() + "',jamulai = " + "'" + jamulai.getEditText().getText().toString() + "',jamakhir = " + "'" + jamakhir.getEditText().getText().toString() + "',dosen = " + "'" + dosen.getEditText().getText().toString() + "',asisten = " + "'" + asisten.getEditText().getText().toString() + "',lab = " + "'" + lab.getEditText().getText().toString() + "',jurusan = " + "'" + jurusan.getEditText().getText().toString() + "' where id = '" + id + "'");
                showToast();
                Intent intent = new Intent().setClass(EditJadwal.this, MainActivity.class);
                startActivity(intent);
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

}