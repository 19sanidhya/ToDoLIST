package com.utilities.sanidhya.todolist;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;


public class AlarmActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this);
        String task_name = getIntent().getStringExtra("task_name");

        //Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_RINGTONES);

        Intent intent=new Intent(this,Main2Activity.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,1,intent,0);
        notificationBuilder.setSmallIcon(android.R.drawable.ic_dialog_alert)
                .setTicker("Alert Alarm For:" + task_name)
                .setContentTitle("Alarm")
                .setContentText("Alert Alarm For:" + task_name)
                .setAutoCancel(true)
        .setContentIntent(pendingIntent)
        .setStyle(new NotificationCompat.BigTextStyle().bigText("SGDSFSDSDBSDBDSGDFB"))
        .setDefaults(NotificationCompat.DEFAULT_ALL );
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(1, notificationBuilder.build());
        AlertDialog.Builder builder = new AlertDialog.Builder(AlarmActivity.this);
        builder.setMessage("Alarm For Task:" + task_name);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                onBackPressed();
            }
        });
        builder.create().show();
    }

}
