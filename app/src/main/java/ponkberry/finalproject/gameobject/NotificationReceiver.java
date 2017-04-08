package ponkberry.finalproject.gameobject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Ponk on 4/6/2017.
 */

public class NotificationReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if(action.equals(NotificationService.notificationServiceStatus)) {
            switch(intent.getIntExtra("NotificationServiceStatus", 0000)) {
                case NotificationService.notifyError:
                    break;
                default:
                    //onError("default");
            }
        }
    }
}
