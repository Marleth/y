package com.gtm.itinerario;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

public class Servicio extends Service {
    private static Sync sync = new Sync();
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static boolean CancelAlarms() {
        try {
            sync.Cancel(context);
            sync.SetConfiguration(context);
            return true;
        }
        catch (Exception ex) {
            return false;
        }
    }

    public static boolean StopAlarms() {
        try {
            sync.Cancel(context);
            return true;
        }
        catch (Exception ex) {
            return false;
        }
    }

    @SuppressWarnings("static-access")
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        this.context = Servicio.this;
        sync.SetConfiguration(this.context);
        return START_STICKY;
    }

    @SuppressWarnings("static-access")
    public void onStart(Context context,Intent intent, int startId) {
        this.context = context;
        sync.SetConfiguration(context);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
