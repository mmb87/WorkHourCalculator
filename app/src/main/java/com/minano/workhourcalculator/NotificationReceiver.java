package com.minano.workhourcalculator;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Admin on 03/05/2017.
 */
public class NotificationReceiver extends BroadcastReceiver {

    private static final String PLAY_PAUSE_INTENT = "playpauseintent";
    private static final String STORE_INTENT = "storeintent";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("TAG", "Here");
        if(intent.getAction().equals(PLAY_PAUSE_INTENT))
        {
            Log.d("TAG", "Here");
        }
        else if(intent.getAction().equals(STORE_INTENT))
        {
            Log.d("TAG", "Here");
        }
    }
}
