package com.example.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DataJadwal extends SQLiteOpenHelper {
    private static final String Database_Name="dbjadwal";

    public DataJadwal(Context context){
        super(context, Database_Name, null, 1);
    }

    public void createTable(SQLiteDatabase db){
        db.execSQL("CREATE TABLE IF NOT EXISTS jadwal(id integer primary key autoincrement, tanggal TEXT, matkul TEXT, jamulai TEXT, jamakhir TEXT, dosen TEXT, asisten TEXT, lab TEXT, jurusan TEXT);");
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public ArrayList<List> getAllData(String date)
    {
        ArrayList<List> arrayList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM jadwal where tanggal = '"+ date +"'", null);

        while(cursor.moveToNext())
        {
            int id = cursor.getInt(0);
            String tanggal = cursor.getString(1);
            String matkul = cursor.getString(2);
            String jamulai = cursor.getString(3);
            String jamakhir = cursor.getString(4);
            String dosen = cursor.getString(5);
            String asisten = cursor.getString(6);
            String lab = cursor.getString(7);
            String jurusan = cursor.getString(8);
            List list = new List(id,tanggal,matkul,jamulai,jamakhir,dosen,asisten,lab,jurusan);

            arrayList.add(list);
        }
        return arrayList;
    }
    public void deleteData(String id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("Delete from jadwal where id = '"+ id +"'");



    }
}
