package com.minano.workhourcalculator.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.minano.workhourcalculator.Data.Entity.WorkHourDay;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 24/12/2016.
 */
public class DataBaseHelper extends SQLiteOpenHelper implements DataBaseHelperActions {

    private static final String DATABASE_NAME = "WorkHoursDataBase";
    private static final String WORKHOURS_TABLE = "WorkHoursTable";
    private static final int DATABASE_VERSION = 1;

    private static final String KEY_ID = "id";
    private static final String KEY_DATE = "date";
    private static final String KEY_NO_OF_WORKHOURS = "numberOfWorkHours";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + WORKHOURS_TABLE + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_DATE + " TEXT,"
                + KEY_NO_OF_WORKHOURS + " INTEGER" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    @Override
    public void storeItem(WorkHourDay workHourDay) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_DATE, workHourDay.getDate());
        values.put(KEY_NO_OF_WORKHOURS, workHourDay.getNumberOfWorkHours());

        // Inserting Row
        db.insert(WORKHOURS_TABLE, null, values);
        db.close(); // Closing database connection
    }

    @Override
    public void deleteItem(WorkHourDay workHourDay) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(WORKHOURS_TABLE, KEY_ID + " = ?",
                new String[] { String.valueOf(workHourDay.getId()) });
        db.close();
    }

    @Override
    public void updateItem(WorkHourDay workHourDay) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_DATE, workHourDay.getDate());
        values.put(KEY_NO_OF_WORKHOURS, workHourDay.getNumberOfWorkHours());

        // updating row
        db.update(WORKHOURS_TABLE, values, KEY_ID + " = ?", new String[]{String.valueOf(workHourDay.getId())});

    }

    @Override
    public WorkHourDay getItem(int id) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(WORKHOURS_TABLE, new String[]{KEY_ID,
                        KEY_DATE, KEY_NO_OF_WORKHOURS}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        WorkHourDay workHourDay = new WorkHourDay();
        workHourDay.setId(Integer.parseInt(cursor.getString(0)));
        workHourDay.setDate(cursor.getString(1));
        workHourDay.setNumberOfWorkHours(cursor.getString(2));

        return workHourDay;
    }

    @Override
    public List<WorkHourDay> getAllItems() {

        List<WorkHourDay> workHourDayList = new ArrayList<WorkHourDay>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + WORKHOURS_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                WorkHourDay workHourDay = new WorkHourDay();
                workHourDay.setId(Integer.parseInt(cursor.getString(0)));
                workHourDay.setDate(cursor.getString(1));
                workHourDay.setNumberOfWorkHours(cursor.getString(2));
                workHourDayList.add(workHourDay);
            } while (cursor.moveToNext());
        }

        return workHourDayList;
    }
}
