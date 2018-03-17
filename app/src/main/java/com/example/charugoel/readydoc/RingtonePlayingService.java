package com.example.charugoel.readydoc;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Charu Goel on 10-02-2018.
 */

public class RingtonePlayingService extends Service {

    MediaPlayer media_song;
    int startId;
    boolean isRunning ;
    Context context;
    Notification myNotification;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("LocalService", "Received start id " + startId + ": " + intent);

        String state = intent.getExtras().getString("extra");

        Log.e("Ringtone state:extra is", state);



        assert state != null;
        switch (state) {
            case "alarm on":
                startId = 1;
                break;
            case "alarm off":
                startId = 0;
                Log.e("Start ID is", state);
                break;
            default:
                startId = 0;
                break;
        }


        if(!this.isRunning && startId == 1){

            Log.e("there is no music", "and you want start");

            media_song = MediaPlayer.create(this, R.raw.mostannoyingsound);
            media_song.start();

            this.isRunning = true;
            this.startId= 0;

            /*Notification n = new Notification(R.drawable.common_google_signin_btn_icon_dark, "alarm", System.currentTimeMillis());
            PendingIntent pi = PendingIntent.getActivity(context, 0, new Intent(), 0);
            n.setLatestEventInfo()*/

           NotificationManager notify_manager = (NotificationManager)this.
                    getSystemService(NOTIFICATION_SERVICE);
            Intent intent_main_activity = new Intent(this.getApplicationContext(), Reminder.class);

            PendingIntent pending_intent_main_activity = PendingIntent.getActivity(this, 0,
                    intent_main_activity, 0);

            Notification.Builder notification_popup = new Notification.Builder(this);
                    notification_popup.setContentTitle("An alarm is going off!");
                    notification_popup.setContentText("Click Me!");
                    notification_popup.setContentIntent(pending_intent_main_activity);
                    notification_popup.setAutoCancel(true);
                    notification_popup.build();

            myNotification = notification_popup.getNotification();

            notify_manager.notify(0, myNotification);


        }
        else if(this.isRunning && startId == 0){

            Log.e("there is music", "and you want end");

            media_song.stop();
            media_song.reset();

            this.isRunning = false;
            this.startId = 0;

        }
        else if(!this.isRunning && startId == 0){

            Log.e("there is no music", "and you want end");

            this.isRunning = false;
            this.startId = 0;

        }
        else if(this.isRunning && startId == 1){

            Log.e("there is music", "and you want start");

            this.isRunning = true;
            this.startId = 1;

        }
        else{
            Log.e("else", "somehow you reached this");

        }


        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {

        // Tell the user we stopped.
        Log.e("on Destroy called", "ta da");

        super.onDestroy();
        this.isRunning = false;
    }

}
