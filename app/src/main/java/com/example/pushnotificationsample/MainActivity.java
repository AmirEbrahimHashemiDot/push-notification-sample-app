package com.example.pushnotificationsample;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

@RequiresApi(api = Build.VERSION_CODES.N)
public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_PERMISSION_CODE = 1001;
    CharSequence channelName = "My Notification Channel";
    String channelId = "channel_id";
    int notificationId = 1;
    String channelDescription = "Description of Notification";
    int importance = NotificationManager.IMPORTANCE_HIGH;
    NotificationChannel notificationChannel;
    NotificationManager notificationManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // <= API 33
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {


            if(checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.POST_NOTIFICATIONS}, REQUEST_PERMISSION_CODE);


            } else {
                notificationChannel = new NotificationChannel(channelId, channelName, importance);
                notificationChannel.setDescription(channelDescription);

                notificationManager = getSystemService(NotificationManager.class);
                notificationManager.createNotificationChannel(notificationChannel);

                NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, channelId)
                        .setSmallIcon(R.drawable.o)
                        .setContentTitle("My Notification")
                        .setContentText("This is my content of Notification.")
                        .setPriority(NotificationCompat.PRIORITY_HIGH);

                NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(MainActivity.this);
                if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                notificationManagerCompat.notify(notificationId, builder.build());
            }
        }

        // between 27, 32
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.S_V2 && Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Toast.makeText(this, "API: " + Build.VERSION.SDK_INT, Toast.LENGTH_SHORT).show();
            notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

            NotificationChannel notificationChannel2 = new NotificationChannel("notif_id", "My Notification", NotificationManager.IMPORTANCE_HIGH);

            if (notificationManager != null) {
                notificationManager.createNotificationChannel(notificationChannel2);
            }

            Notification notification = new NotificationCompat.Builder(this, "notif_id")
                    .setSmallIcon(android.R.drawable.ic_menu_sort_by_size)
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.o))
                    .setContentTitle("Test Title")
                    .setContentText("Test Text")
                    .build();

            notificationManager.notify(1, notification);
        }

        // > 27
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            Toast.makeText(this, "API: " + Build.VERSION.SDK_INT, Toast.LENGTH_SHORT).show();

            //notificationChannel = new NotificationChannel("notif_id", "My Notification", NotificationManager.IMPORTANCE_HIGH);

            /*if (notificationManager != null) {
                notificationManager.createNotificationChannel(notificationChannel);
            }*/

            Notification notification = new NotificationCompat.Builder(this)
                    .setSmallIcon(android.R.drawable.ic_menu_sort_by_size)
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.o))
                    .setContentTitle("Test Title")
                    .setContentText("Test Text")
                    .build();

            notificationManager.notify(1001, notification);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION_CODE && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "You accepted permission.", Toast.LENGTH_SHORT).show();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notificationChannel = new NotificationChannel(channelId, channelName, importance);
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notificationChannel.setDescription(channelDescription);
            }

            notificationManager = getSystemService(NotificationManager.class);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notificationManager.createNotificationChannel(notificationChannel);
            }

            NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, channelId)
                    .setSmallIcon(R.drawable.o)
                    .setContentTitle("My Notification")
                    .setContentText("This is my content of Notification.")
                    .setPriority(NotificationCompat.PRIORITY_HIGH);

            NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(MainActivity.this);
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            notificationManagerCompat.notify(notificationId, builder.build());
        } else {
            Toast.makeText(this, "You denied permission.", Toast.LENGTH_SHORT).show();
        }
    }

    public void sendNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel = new NotificationChannel(channelId, channelName, importance);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel.setDescription(channelDescription);
        }

        notificationManager = getSystemService(NotificationManager.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannel(notificationChannel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, channelId)
                .setSmallIcon(R.drawable.o)
                .setContentTitle("My Notification")
                .setContentText("This is my content of Notification.")
                .setPriority(NotificationCompat.PRIORITY_HIGH);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(MainActivity.this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        notificationManagerCompat.notify(notificationId, builder.build());
    }

}

/*
package com.example.pushnotificationsample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    NotificationChannel notificationChannel;
    NotificationManager notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            notificationChannel = new NotificationChannel("notif_id", "My Notification", NotificationManager.IMPORTANCE_HIGH);

            if (notificationManager != null) {
                notificationManager.createNotificationChannel(notificationChannel);
            }

            Notification notification = new NotificationCompat.Builder(this, "notif_id")
                    .setSmallIcon(android.R.drawable.ic_menu_sort_by_size)
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.o))
                    .setContentTitle("Test Title")
                    .setContentText("Test Text")
                    .build();

            notificationManager.notify(1, notification);
        } else {
            Notification notification = new NotificationCompat.Builder(this)
                    .setSmallIcon(android.R.drawable.ic_menu_sort_by_size)
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.o))
                    .setContentTitle("Test Title")
                    .setContentText("Test Text")
                    .build();

            notificationManager.notify(1001, notification);
        }
    }
}*/