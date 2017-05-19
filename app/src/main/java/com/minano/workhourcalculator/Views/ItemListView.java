package com.minano.workhourcalculator.Views;

import com.minano.workhourcalculator.Data.Entity.WorkHourDay;

import java.util.List;

/**
 * Created by Admin on 31/03/2017.
 */
public interface ItemListView extends MvpView{
    void renderItemList(List<WorkHourDay> itemList);
}
