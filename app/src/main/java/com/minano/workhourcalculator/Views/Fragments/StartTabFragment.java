package com.minano.workhourcalculator.Views.Fragments;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.RemoteViews;
import android.widget.TextView;

import com.minano.workhourcalculator.Data.Entity.WorkHourDay;
import com.minano.workhourcalculator.Domain.UseCaseActions;
import com.minano.workhourcalculator.Domain.UseCaseInteractor;
import com.minano.workhourcalculator.MainActivity;
import com.minano.workhourcalculator.R;
import com.minano.workhourcalculator.Views.ItemAdapter;
import com.minano.workhourcalculator.Views.PausableChronometer;
import com.minano.workhourcalculator.WorkHourCalculator;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Admin on 10/02/2017.
 */
public class StartTabFragment extends Fragment implements View.OnClickListener {

    private static final String DATE_FORMAT = "EEE, d MMM yyyy";
    private static final int NOTIFICATION_ID = 0;
    private static final String PLAY_PAUSE_INTENT = "playpauseintent";
    private static final String STORE_INTENT = "storeintent";

    private Context context;
    private Activity activity;
    private PausableChronometer chronometer;
    private TextView dateTextView;
    private FloatingActionButton playPauseButton;
    private FloatingActionButton storeButton;
    private Boolean isClockRunning = false;
    private UseCaseActions useCaseInteractor;
    private ItemAdapter adapter;
    private int imagePath;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            this.context = context;
            this.activity = (Activity) context;
            useCaseInteractor = new UseCaseInteractor(activity);
            WorkHourCalculator app = (WorkHourCalculator) activity.getApplication();
            adapter = app.getAdapter();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.timer_layout, container, false);

        DateFormat df = new SimpleDateFormat(DATE_FORMAT);

        chronometer = (PausableChronometer) view.findViewById(R.id.chronometer);
//        chronometer.setFormat("hh:mm:ss");

        dateTextView = (TextView) view.findViewById(R.id.date_textview);
        dateTextView.setText(df.format(new Date()));
        playPauseButton = (FloatingActionButton) view.findViewById(R.id.fab_start_stop);
        playPauseButton.setOnClickListener(this);
        storeButton = (FloatingActionButton) view.findViewById(R.id.fab_store);
        storeButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.fab_start_stop:
                if (!isClockRunning) {
                    startCount();
                } else {
                    stopCount();
                }
                break;
            case R.id.fab_store:
                storeInfoInDataBase();
                break;
        }

    }

    private void startCount() {
        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.start();
        isClockRunning = true;
        imagePath = R.drawable.ic_pause_circle_filled_white_24dp;
        playPauseButton.setImageResource(imagePath);
        showNotification();
    }

    private void stopCount() {
        chronometer.stop();
        isClockRunning = false;
        imagePath = R.drawable.ic_play_arrow_white_24dp;
        playPauseButton.setImageResource(imagePath);
    }

    private void storeInfoInDataBase() {
        WorkHourDay workHourDay = new WorkHourDay();

        workHourDay.setDate(dateTextView.getText().toString());
        workHourDay.setNumberOfWorkHours(chronometer.getText().toString());

        useCaseInteractor.storeWorkingDay(workHourDay);

        adapter.recalculateList();
    }

    private void showNotification() {

        Intent playPauseButtonIntent = new Intent(context, NotificationReceiver.class);
        playPauseButtonIntent.setAction(PLAY_PAUSE_INTENT);
        PendingIntent pendingPlayPauseButtonIntent = PendingIntent.getBroadcast(context, NOTIFICATION_ID, playPauseButtonIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Action PlayPauseButtonAction = new NotificationCompat.Action.Builder(imagePath, "", pendingPlayPauseButtonIntent).build();

        Intent storeButtonIntent = new Intent(context, NotificationReceiver.class);
        storeButtonIntent.setAction(STORE_INTENT);
        PendingIntent pendingStoreButtonIntent = PendingIntent.getBroadcast(context, NOTIFICATION_ID, storeButtonIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Action storeButtonAction = new NotificationCompat.Action.Builder(R.drawable.ic_save_white_24dp, "", pendingStoreButtonIntent).build();

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_action_name)
                .setContentTitle(getResources().getString(R.string.app_name))
                .setContentText("Running...")
                .addAction(PlayPauseButtonAction)
                .addAction(storeButtonAction);

        // Creates an explicit intent for an Activity in your app
        Intent resultIntent = new Intent(context, MainActivity.class);

        // The stack builder object will contain an artificial back stack for the
        // started Activity.
        // This ensures that navigating backward from the Activity leads out of
        // your application to the Home screen.
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(activity);
        // Adds the back stack for the Intent (but not the Intent itself)
        stackBuilder.addParentStack(MainActivity.class);
        // Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
//        PendingIntent resultPendingIntent = PendingIntent.getActivity(context, 0, resultIntent, 0);
        mBuilder.setContentIntent(resultPendingIntent);

        NotificationManager notificationManager = (NotificationManager) activity.getSystemService(Context.NOTIFICATION_SERVICE);
        // mId allows you to update the notification later on.
        notificationManager.notify(NOTIFICATION_ID, mBuilder.build());
    }

    public static class NotificationReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            Log.d("TAG", "Here");
            if(intent.getAction().equals(PLAY_PAUSE_INTENT))
            {
//                Log.d("TAG", "Here");
            }
            else if(intent.getAction().equals(STORE_INTENT))
            {
//                Log.d("TAG", "Here");
            }
        }
    }
}




