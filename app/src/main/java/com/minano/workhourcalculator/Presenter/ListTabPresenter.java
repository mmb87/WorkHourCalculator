package com.minano.workhourcalculator.Presenter;

import com.minano.workhourcalculator.Data.Entity.WorkHourDay;
import com.minano.workhourcalculator.Domain.ItemListCallback;
import com.minano.workhourcalculator.Domain.UseCaseActions;
import com.minano.workhourcalculator.Domain.UseCaseInteractor;
import com.minano.workhourcalculator.Utils.ToastMaker;
import com.minano.workhourcalculator.Views.ItemListView;

import java.util.List;

/**
 * Created by Admin on 30/03/2017.
 */
public class ListTabPresenter implements Presenter<ItemListView>, ItemListCallback {

    private ItemListView itemListView;
    private UseCaseActions useCaseInteractor;

    public ListTabPresenter(){

    }

    @Override
    public void onResponse(List<WorkHourDay> itemList) {
        itemListView.renderItemList(itemList);
    }

    @Override
    public void setView(ItemListView view) {
        if(view == null) ToastMaker.ShowOnScreen(view.getContext(), "You cannot render a null View");
        itemListView = view;
        useCaseInteractor = new UseCaseInteractor(view.getContext());
        useCaseInteractor.getWorkingDayList();
    }

    @Override
    public void detachView() {

    }
}
