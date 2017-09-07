package com.example.windows10timt.myweather.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Windows 10 TIMT on 1/11/2017.
 */

public class SQLHelper extends SQLiteOpenHelper {
    static final String DB_NAME = "TestWeather";

    public SQLHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTable = "create table TestWeather(" +
                "id integer primary key autoincrement," +
                "city text," +
                "temp text," +
                "speed text," +
                "humidity text," +
                "pressure text" +
                ");";
        sqLiteDatabase.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        if (i == i1) {
            sqLiteDatabase.execSQL("drop if exists TestWeather");
            onCreate(sqLiteDatabase);
        }
    }


    public void insertWeather(String city, String temp, String speed, String humidity, String pressure) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("city", city);
        values.put("temp", temp);
        values.put("speed", speed);
        values.put("humidity", humidity);
        values.put("pressure", pressure);
        db.insert("TestWeather", null, values);
    }

    public void updateWeather(int id, String city, String temp, String speed, String humidity, String pressure) {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id",id);
        values.put("city", city);
        values.put("temp", temp);
        values.put("speed", speed);
        values.put("humidity", humidity);
        values.put("pressure", pressure);
        db.update("TestWeather", values, "id = ?", new String[]{String.valueOf(id)});
    }

    public ArrayList<SQLProduct> getAllWeather() {
        ArrayList<SQLProduct> sqlProducts = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(false, "TestWeather", null, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String city = cursor.getString(cursor.getColumnIndex("city"));
            String temp = cursor.getString(cursor.getColumnIndex("temp"));
            String speed = cursor.getString(cursor.getColumnIndex("speed"));
            String humidity = cursor.getString(cursor.getColumnIndex("humidity"));
            String pressure = cursor.getString(cursor.getColumnIndex("pressure"));
            sqlProducts.add(new SQLProduct(city, temp, speed, humidity, pressure));
        }
        return sqlProducts;
    }
}
