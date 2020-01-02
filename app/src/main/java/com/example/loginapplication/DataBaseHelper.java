package com.example.loginapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DataBaseHelper extends SQLiteOpenHelper {
    final static String DATABASE_NAME = "register_db";
    final static String TABLE_NAME = "register_user";
    final static String COL_1 = "ID";
    final static String COL_2 = "username";
    final static String COL_3 = "password";
    final static String COL_4 = "age";
    final static String COL_5 = "weight";
    final static String COL_6 = "height";

    public DataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE register_user (ID INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, password TEXT, age INTEGER, weight INTEGER, height INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);

    }

    public Long registerUser (String username, String password, int age,  int weight, int height) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, username);
        contentValues.put(COL_3, password);
        contentValues.put(COL_4, age);
        contentValues.put(COL_5, weight);
        contentValues.put(COL_6, height);

        Long res = db.insert(TABLE_NAME, null, contentValues);
        db.close();
        return res;
    }

    public int checkUser (String username, String password) {
        String[] columns = { COL_1 };
        SQLiteDatabase db = getReadableDatabase();
        String selection = COL_2 + "=?" + " and " + COL_3 + "=?";
        String[] selectionArgs = {username, password};
        Cursor cursor = db.query(TABLE_NAME,columns,selection,selectionArgs,null,null,null);
        int count = cursor.getCount();

        if (count > 0) {
            int id_new = 0;
            if (cursor.moveToFirst()) {
                id_new = cursor.getInt(cursor.getColumnIndex("ID"));
                System.out.printf(">>>>>>>>>>>>>"+id_new);
            }
            return id_new;
        }
        else {
            return 0;
        }
    }

    public ArrayList<Object> getDetails (int id) {
        ArrayList<Object> result = new ArrayList<>();
        String[] columns = { COL_1, COL_2, COL_3, COL_4, COL_5, COL_6 };
        SQLiteDatabase db = getReadableDatabase();
        String selection = COL_1 + "=?";
        String[] selectionArgs = {String.valueOf(id)};
        Cursor cursor = db.query(TABLE_NAME,columns,selection,selectionArgs,null,null,null);
        //Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, selectionArgs);

        if (cursor.moveToFirst()) {
            int age = cursor.getInt(cursor.getColumnIndex("age"));
            int height = cursor.getInt(cursor.getColumnIndex("height"));
            int weight = cursor.getInt(cursor.getColumnIndex("weight"));

            result.add(age);
            result.add(height);
            result.add(weight);
        }

        return result;
    }

    public int updateUser (int id, int age, int height, int weight) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_4,age); //These Fields should be your String values of actual column names
        cv.put(COL_5,weight);
        cv.put(COL_6,height);

        int res = db.update(TABLE_NAME, cv, "ID="+id, null);

        return res;
    }
}
