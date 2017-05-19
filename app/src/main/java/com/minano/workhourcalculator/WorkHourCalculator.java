package com.minano.workhourcalculator;

import android.app.Application;

import com.minano.workhourcalculator.Views.ItemAdapter;

/**
 * Created by Admin on 31/03/2017.
 */
public class WorkHourCalculator extends Application {

    private ItemAdapter adapter;

    @Override
    public void onCreate() {
        super.onCreate();
        adapter = new ItemAdapter(this);
    }

    public ItemAdapter getAdapter() {
        return adapter;
    }

}
