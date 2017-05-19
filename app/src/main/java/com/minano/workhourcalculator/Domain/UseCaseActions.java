package com.minano.workhourcalculator.Domain;

import com.minano.workhourcalculator.Data.Entity.WorkHourDay;

import java.util.List;

/**
 * Created by Admin on 28/12/2016.
 */
public interface UseCaseActions {
    void storeWorkingDay(WorkHourDay workHourDay);
    void deleteWorkingDay(WorkHourDay workHourDay);
    void updateWorkingDay(WorkHourDay workHourDay);
    WorkHourDay getWorkingDay(int id);
    List<WorkHourDay> getWorkingDayList();
}
