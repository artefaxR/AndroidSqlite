package com.example.androidsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {


    public DBHelper(@Nullable Context context) {
        super(context, "dbmhs", null, 1); // MEMBUAT DATABASE
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String smt = "CREATE TABLE mhs(id INTEGER PRIMARY KEY, nama VARCHAR, nim VARCHAR, nohp VARCHAR)"; // MEMBUAT TABEL
        sqLiteDatabase.execSQL(smt);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    void simpan(Mhs mhs){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nama", mhs.getNama());
        contentValues.put("nim", mhs.getNim());
        contentValues.put("nohp", mhs.getNoHp());

        sqLiteDatabase.insert("mhs", null,  contentValues);
    }


    ArrayList<Mhs> lihat(){
        String query  = "SELECT * FROM mhs";
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        ArrayList<Mhs> arrayList = new ArrayList<>();
        while(cursor.moveToNext()){
            Mhs mhs = new  Mhs(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
            arrayList.add(mhs);
        }

        return arrayList;
    }
}
