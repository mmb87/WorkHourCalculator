package com.minano.workhourcalculator.Views.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.minano.workhourcalculator.Data.Entity.WorkHourDay;
import com.minano.workhourcalculator.Domain.UseCaseActions;
import com.minano.workhourcalculator.Domain.UseCaseInteractor;
import com.minano.workhourcalculator.R;
import com.minano.workhourcalculator.Views.ItemAdapter;
import com.minano.workhourcalculator.WorkHourCalculator;

/**
 * Created by Admin on 10/02/2017.
 */
public class ListTabFragment extends Fragment {

    private RecyclerView recyclerView;
    private ItemAdapter adapter;
    private Activity activity;
    private UseCaseActions useCaseInteractor;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            this.activity = (Activity) context;
            WorkHourCalculator app = (WorkHourCalculator) activity.getApplication();
            adapter = app.getAdapter();
            useCaseInteractor = new UseCaseInteractor(context);
            adapter.setDetailItemList(useCaseInteractor.getWorkingDayList());
//            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.data_list_layout, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        LinearLayoutManager managerLayout = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(managerLayout);
//        adapter.setHasStableIds(true);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG", "Pasa por aqui...");
                WorkHourDay workHourDay = adapter.getItem(recyclerView.getChildAdapterPosition(v));
                showDeleteDialog(workHourDay);
            }
        });

        return view;
    }

    public void showDeleteDialog(final WorkHourDay workHourDay){

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Are you sure you want to delete entry?");
        builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                useCaseInteractor.deleteWorkingDay(workHourDay);
                adapter.recalculateList();
                dialog.dismiss();
            }
        });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}
