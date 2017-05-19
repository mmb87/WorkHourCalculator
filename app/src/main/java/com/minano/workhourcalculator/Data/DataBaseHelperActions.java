package com.minano.workhourcalculator.Data;

import com.minano.workhourcalculator.Data.Entity.WorkHourDay;

import java.util.List;

/**
 * Created by Admin on 24/12/2016.
 */
public interface DataBaseHelperActions {
    void storeItem(WorkHourDay workHourDay);
    void deleteItem(WorkHourDay workHourDay);
    void updateItem(WorkHourDay workHourDay);
    WorkHourDay getItem(int id);
    List<WorkHourDay> getAllItems();

}
