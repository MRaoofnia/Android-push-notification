package com.example.root.pn2test;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button simplePush = (Button)findViewById(R.id.simple);
        simplePush.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //creating a simple push notification
                //setting notification's content
                String CHANNEL_ID="ID";//just for compatibility for Oreo
                NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(MainActivity.this, CHANNEL_ID)
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .setContentTitle("Title")
                        .setContentText("it's a push notification.")
                        .setStyle(new NotificationCompat.BigTextStyle() //here it is to perform an expandable notification(with expandable text
                                .bigText("Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."))
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);
                //showing it
                NotificationManagerCompat NMC = NotificationManagerCompat.from(MainActivity.this);
                NMC.notify(0, mBuilder.build());//better to use a variable instead of a number(e.g. 0). but it depends on you
            }
        });

        Button tap = (Button)findViewById(R.id.tap);
        tap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //this notification is the same as the simple notification but when you tap on it you can open the activity
                //creating the intent
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);//for when the activity is active and we want just to restart it not opening a new activity
                PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, intent, 0);
                //creating the notification
                String CHANNEL_ID="ID";
                NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(MainActivity.this, CHANNEL_ID)
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .setContentTitle("Title")
                        .setContentText("it's a push notification with tap abilities.")
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setContentIntent(pendingIntent)//this will add the intent to the notification
                        .setAutoCancel(true);//this causes auto canceling. when user taps on notification it will be destructed.
                //showing
                NotificationManagerCompat NMC = NotificationManagerCompat.from(MainActivity.this);
                NMC.notify(1, mBuilder.build());

            }
        });

    }
}
