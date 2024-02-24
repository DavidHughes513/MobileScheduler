package Hughes.termscheduler.Recievers;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

import Hughes.termscheduler.R;

public class AssessmentStartReceiver extends BroadcastReceiver {
    String channel_id = "Assessment Start";

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        createNotificationChannel(context,channel_id);
        Notification notification = new NotificationCompat.Builder(context,channel_id)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentText(intent.getStringExtra("assessmentStartMSG"))
                .setContentTitle("New Assessment Begins!").build();

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(CourseReceiver.notificationID++, notification);
    }


    private void createNotificationChannel(Context context, String CHANNEL_ID){
        CharSequence channelName = "Start Assessment";
        String description = "Notification for when assessment begins";
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, channelName, importance);
        channel.setDescription(description);
        NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);
    }

}