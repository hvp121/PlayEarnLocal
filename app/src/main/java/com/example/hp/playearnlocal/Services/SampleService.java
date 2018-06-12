package com.example.hp.playearnlocal.Services;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

public class SampleService extends IntentService {

    int serviceCount = 0;
    public SampleService() {
        super("SampleService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        serviceCount = intent.getIntExtra("Count",0);
        Log.i("Service","Service Number"+serviceCount);

        try {
            Log.i("Service","Service "+serviceCount+" Started");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // Restore interrupt status.
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("Service","Service "+serviceCount+" Destroy");

    }
}
