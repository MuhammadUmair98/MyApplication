package com.mubashir.myapplication;

import android.annotation.SuppressLint;
import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class RSSPullService extends Service {
    long realSeconds;
    SharedPreferences preferences;
    SharedPreferences preferencef;
    SharedPreferences preferenceS;
    SharedPreferences preferenceD;
    SharedPreferences preferenceC;

    public RSSPullService() {
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        onTaskRemoved(intent);


            realSeconds = record.seconds-86340;
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        preferencef = PreferenceManager.getDefaultSharedPreferences(this);
        preferenceS = PreferenceManager.getDefaultSharedPreferences(this);
        preferenceD = PreferenceManager.getDefaultSharedPreferences(this);
        preferenceC = PreferenceManager.getDefaultSharedPreferences(this);


        new CountDownTimer(Long.valueOf( preferences.getLong("Value", 0)), 1000) {

            public void onTick(long millisUntilFinished) {





                //here you can have your logic to set text to edittext

            }

            public void onFinish() {

                NotificationCompat.Builder mBuilder =   new NotificationCompat.Builder(RSSPullService.this)
                        // notification icon
                        .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                        .setContentTitle("Dear "+preferenceS.getString("Son", "")+" s/d/o "+preferencef.getString("Father", "")) // title for notification
                        .setContentText("Your Appointment Date is "+preferencef.getString("Date", "")+"") // message for notification
                        .setAutoCancel(true); // clear notification after click
                Intent intent = new Intent(RSSPullService.this, record.class);
                @SuppressLint("WrongConstant") PendingIntent pi = PendingIntent.getActivity(RSSPullService.this,0,intent,PendingIntent.FLAG_NO_CREATE);
                mBuilder.setContentIntent(pi);
                NotificationManager mNotificationManager =
                        (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                mNotificationManager.notify(0, mBuilder.build());
                Toast.makeText(getApplicationContext(),String.valueOf("App Notification"),Toast.LENGTH_SHORT).show();
                MediaPlayer mediaPlayer = MediaPlayer.create(RSSPullService.this, R.raw.sharp);
                mediaPlayer.start();
                String   phoneNumber=String.valueOf(preferenceS.getString("Number", ""));
                String  message="Dear "+preferenceS.getString("Son", "") +" s/d/o of "+preferencef.getString("Father", "")+" your data is successfully saved your appointment" +
                        " date is "+preferencef.getString("Date", "");
                sendSMS(phoneNumber,message);
            }

        }.start();

        return START_STICKY;
    }
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
    private void sendSMS(String phoneNumber, String message) {
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, null, null);
    }

}