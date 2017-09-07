package com.example.windows10timt.myweather.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Windows 10 TIMT on 1/12/2017.
 */

public class SQLHelper2 extends SQLiteOpenHelper {
    static final String DB_NAME = "TestWeather2";

    public SQLHelper2(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTable = "create table TestWeather2(" +
                "id integer primary key autoincrement," +
                "day text," +
                "date text," +
                "description text," +
                "high text," +
                "low text);";
        sqLiteDatabase.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        if (i == i1) {
            sqLiteDatabase.execSQL("drop if exists TestWeather2");
            onCreate(sqLiteDatabase);
        }
    }


    public void insertWeather2(String day, String date, String description, String high, String low) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("day", day);
        values.put("date", date);
        values.put("description", description);
        values.put("high", high);
        values.put("low", low);

        db.insert("TestWeather2", null, values);
    }

    public void updateWeather2(int id, String day, String date, String description, String high, String low) {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("rowid", id);
        values.put("day", day);
        values.put("date", date);
        values.put("description", description);
        values.put("high", high);
        values.put("low", low);
        db.update("TestWeather2", values, "rowid = ?", new String[]{String.valueOf(id)});
    }

    public ArrayList<SQLProduct2> getAllWeather2() {
        ArrayList<SQLProduct2> sqlProducts = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(false, "TestWeather2", null, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String day = cursor.getString(cursor.getColumnIndex("day"));
            String date = cursor.getString(cursor.getColumnIndex("date"));
            String description = cursor.getString(cursor.getColumnIndex("description"));
            String high = cursor.getString(cursor.getColumnIndex("high"));
            String low = cursor.getString(cursor.getColumnIndex("low"));

            sqlProducts.add(new SQLProduct2(day, date,description, high, low));
        }
        return sqlProducts;
    }
}
