package com.minano.workhourcalculator.Domain;

import android.content.Context;

import com.minano.workhourcalculator.Data.DataBaseHelper;
import com.minano.workhourcalculator.Data.DataBaseHelperActions;
import com.minano.workhourcalculator.Data.Entity.WorkHourDay;

import java.util.List;

/**
 * Created by Admin on 28/12/2016.
 */
public class UseCaseInteractor implements UseCaseActions {

    private DataBaseHelperActions repository;

    public UseCaseInteractor (Context context) {
        repository = new DataBaseHelper(context);
    }

    @Override
    public void storeWorkingDay(WorkHourDay workHourDay) {
        repository.storeItem(workHourDay);
    }

    @Override
    public void deleteWorkingDay(WorkHourDay workHourDay) {
        repository.deleteItem(workHourDay);
    }

    @Override
    public void updateWorkingDay(WorkHourDay workHourDay) {
        repository.updateItem(workHourDay);
    }

    @Override
    public WorkHourDay getWorkingDay(int id) {
        return repository.getItem(id);
    }

    @Override
    public List<WorkHourDay> getWorkingDayList() {
        return repository.getAllItems();
    }
}
