package com.minano.workhourcalculator.Views;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.minano.workhourcalculator.Data.Entity.WorkHourDay;
import com.minano.workhourcalculator.Domain.UseCaseActions;
import com.minano.workhourcalculator.Domain.UseCaseInteractor;
import com.minano.workhourcalculator.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 10/02/2017.
 */
public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder>  {

    private LayoutInflater inflater;
    private List<WorkHourDay> detailItemList;
    private UseCaseActions useCaseInteractor;

    private View.OnClickListener onClickListener;

    public ItemAdapter(Context context){
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        detailItemList = new ArrayList();
        useCaseInteractor = new UseCaseInteractor(context);
//        detailItemList = useCaseInteractor.getWorkingDayList();
//        notify();
    }

    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_data_list_layout, null);
        v.setOnClickListener(onClickListener);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ItemAdapter.ViewHolder holder, int position) {
        holder.nOfWorkingHours.setText(detailItemList.get(position).getNumberOfWorkHours());
        holder.monthTextView.setText(detailItemList.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return detailItemList.size();
    }

    public void setOnItemClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void setDetailItemList(List<WorkHourDay> detailItemList) {
        this.detailItemList = detailItemList;
    }

    public WorkHourDay getItem (int position){
        return detailItemList.get(position);
    }

    public void recalculateList(){
        detailItemList = useCaseInteractor.getWorkingDayList();
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView monthTextView;
        public TextView nOfWorkingHours;

        public ViewHolder(View itemView) {
            super(itemView);

            monthTextView = (TextView) itemView.findViewById(R.id.month_textview);
            nOfWorkingHours = (TextView) itemView.findViewById(R.id.n_of_hours_textview);
        }
    }
}
