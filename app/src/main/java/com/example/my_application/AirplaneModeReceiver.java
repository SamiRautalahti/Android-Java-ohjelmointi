package com.example.my_application;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

public class AirplaneModeReceiver extends BroadcastReceiver {

    public static final String  TAG = "AirplaneReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        //throw new UnsupportedOperationException("Not yet implemented");
        Log.e(TAG, "tuleeko onReceive()-metodiin????");

        boolean state = intent.getBooleanExtra("state", false);
        Log.e(TAG, "APM-receiver toimii(ehkä)" + state);


            context = context.getApplicationContext();
            CharSequence Ontext = "Airplane mode ON!";
            CharSequence Offtext = "Airplane mode OFF!";
            int duration = Toast.LENGTH_LONG;

        if(state == true) {
            Toast toast = Toast.makeText(context, Ontext, duration);
            toast.show();
        }
        else {
                Toast toast = Toast.makeText(context, Offtext, duration);
                toast.show();
        }



        }

    }
