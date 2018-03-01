package com.gtm.itinerario;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.PowerManager;
import android.util.Log;

import com.DatabaseSync.Operation;
import com.DatabaseSync.Synchronization;

/**
 * Created by PRACTICAS on 23/06/2016.
 */
public class    Sync  extends BroadcastReceiver {
    public Context context;
    public Synchronization sync;

    @SuppressLint("Wakelock")
    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;
        sync = new Synchronization(this.context);
        sync.UseUrlConnection(false);
        enviar_mensaje.sendEmptyMessage(0);
    }

    @SuppressLint({ "Wakelock", "HandlerLeak" })
    private Handler enviar_mensaje = new Handler() {
        public void handleMessage(Message msg) {
            PowerManager powerManager = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
            PowerManager.WakeLock wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "NO sleep");
            wakeLock.acquire();
            //Toast.makeText(context, "Iniciando proceso de Sincronizacion", Toast.LENGTH_LONG).show();
            try {
                int connectionStatus = sync.getStatusConnection();
                if (connectionStatus == Operation.CONNECTION_STATUS_BOOTH_OK || connectionStatus == Operation.
                            CONNECTION_STATUS_WIFI_OK || connectionStatus == Operation.CONNECTION_STATUS_MOBILE_OK) {
                    //sync.ApplyChanges();
                    sync.ApplyChanges(3);
                    Log.e(getClass().getSimpleName(), "Sincronizando Datos...");
                    //Toast.makeText(context, "Sincronizando Datos...", Toast.LENGTH_LONG).show();
                    sync.clearQueue();
                }
            } catch (Exception e) {
                Log.e(getClass().getSimpleName(), "ERROR SYNC ALARM: " + e.getLocalizedMessage());
                //Toast.makeText(context, "ERROR SYNC ALARM: " + e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
            //Toast.makeText(context, "Finalizando proceso de Sincronizacion", Toast.LENGTH_LONG).show();
            wakeLock.release();
        }
    };

    public void SetConfiguration(Context context) {
        AlarmManager am = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent i = new Intent(context, Sync.class);
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, i, 0);
        am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 1000 * 60 * 5, pi); // Millisec * Second * Minute
    }

    public void Cancel(Context context) {
        Intent intent = new Intent(context, Sync.class);
        PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(sender);
    }
}