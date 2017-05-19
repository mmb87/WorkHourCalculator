package com.minano.workhourcalculator.Utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Admin on 31/03/2017.
 */
public class ToastMaker {
    public static void ShowOnScreen(Context context, String text){
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }
}
