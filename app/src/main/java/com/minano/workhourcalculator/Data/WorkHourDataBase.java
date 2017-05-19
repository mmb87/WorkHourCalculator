package com.minano.workhourcalculator.Data;

import android.content.Context;

import com.minano.workhourcalculator.Data.Entity.WorkHourDay;

/**
 * Created by Admin on 10/02/2017.
 */
public class WorkHourDataBase {

    private static DataBaseHelper instance = null;

    protected WorkHourDataBase(){

    }

    public static DataBaseHelper getInstance(Context context) {
        if(instance == null) {
            instance = new DataBaseHelper(context);
        }
        return instance;
    }

}
