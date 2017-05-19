package com.minano.workhourcalculator.Domain;

import com.minano.workhourcalculator.Data.Entity.WorkHourDay;

import java.util.List;

/**
 * Created by Admin on 31/03/2017.
 */
public interface ItemListCallback {
    void onResponse(List<WorkHourDay> itemList);
}
