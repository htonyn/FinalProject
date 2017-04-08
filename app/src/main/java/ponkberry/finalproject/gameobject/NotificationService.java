package ponkberry.finalproject.gameobject;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

import ponkberry.finalproject.MainViewPager;
import ponkberry.finalproject.R;
import ponkberry.finalproject.util.UtilLog;

/**
 * Created by Ponk on 4/3/2017.
 */

public class NotificationService extends Service {

    public static final String notificationServiceStatus = "ponkberry.finalproject.NotificationServiceStatus";
    private int NOTIFICATION_ID = 12345;
    private NotificationCompat.Builder builder;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        UtilLog.logD("Service", "onCreate");
        builder = new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.tacocat_halloween)
                        .setContentTitle("Profile Updated")
                        .setContentText("Achievements have been synced.");

        Intent targetIntent = new Intent(this, MainViewPager.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, targetIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);
    }

    public void notificationBuilder() {
        NotificationManager nManager = (NotificationManager) getSystemService(this.NOTIFICATION_SERVICE);
        nManager.notify(NOTIFICATION_ID, builder.build());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        UtilLog.logD("Service", "onStartCommand");

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    notificationBuilder();
                } catch (Exception e) {
                    e.printStackTrace();
                    notifyError("System_ERROR: "+e.toString());
                }
            }
        }).start();

        return Service.START_STICKY;
    }

    public static final int notifyError = 0000;

    private void notifyError(String error) {
        Intent intent = new Intent();
        intent.putExtra("NotificationService", notifyError);
        intent.putExtra("notifyError", error);
        sendNotify(intent);
    }

    private void sendNotify(Intent intent) {
        intent.setAction(notificationServiceStatus);
        sendBroadcast(intent);
    }



}
