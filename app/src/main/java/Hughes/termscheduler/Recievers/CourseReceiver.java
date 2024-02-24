package Hughes.termscheduler.Recievers;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import Hughes.termscheduler.R;

public class CourseReceiver extends BroadcastReceiver {
    static int notificationID;
    String channel_id = "Course Start";

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        //System.out.println("Received");\\
        createNotificationChannel(context,channel_id);
        Notification notification = new NotificationCompat.Builder(context,channel_id)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentText(intent.getStringExtra("courseStartMSG"))
                .setContentTitle("New Course Begins!").build();

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(notificationID++, notification);

    }

    private void createNotificationChannel(Context context, String CHANNEL_ID){
        CharSequence channelName = "Start Course";
        String description = "Notification for when course begins";
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, channelName, importance);
        channel.setDescription(description);
        NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);
    }
}