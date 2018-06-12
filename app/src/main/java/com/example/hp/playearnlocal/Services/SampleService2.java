package com.example.hp.playearnlocal.Services;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.content.Context;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;


public class SampleService2 extends Service {
    int serviceCount;
    Thread t;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        serviceCount = intent.getIntExtra("Count",0);
        Log.i("Service","Service Number"+serviceCount);

         t = new Thread(new Runnable() {
            @Override
            public void run() {
                Log.i("Service","Service "+serviceCount+" Started");
                try {
                    t.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                t.currentThread().interrupt();
            }
        });
         t.run();

        stopService(intent);
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("Service","Service "+serviceCount+" Destroy");
    }
}
