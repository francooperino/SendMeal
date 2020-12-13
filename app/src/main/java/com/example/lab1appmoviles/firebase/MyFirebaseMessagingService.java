package com.example.lab1appmoviles.firebase;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.example.lab1appmoviles.MainActivity;
import com.example.lab1appmoviles.MyNotificationPublisher;
import com.example.lab1appmoviles.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private final static String CHANNEL_ID = "NOTIFICACIONPEDIDOLISTO";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // ...
        Log.d("DEBUG", "From: " + remoteMessage.getFrom());

        // Pueden validar si el mensaje trae datos
        if (remoteMessage.getData().size() > 0) {
            Log.d("DEBUG", "Payload del mensaje: " + remoteMessage.getData());

        }
        // Pueden validar si el mensaje trae una notificación
        if (remoteMessage.getNotification() != null) {
            Log.d("NOTIFICACION", "Cuerpo de la notificación del mensaje: " + remoteMessage.getNotification().getBody());
            sendNotification(remoteMessage.getNotification().getBody());
        }


    }

    // Función para crear una notificación (completar)
    private void sendNotification(String messageBody) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "Notificaciones de pedidos";
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, name, NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);
        }


        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.sendmeal)
                .setContentTitle("Pedido Confirmado")
                .setContentText(messageBody)
                .setColor(Color.BLUE)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setLights(Color.MAGENTA, 1000, 1000)
                .setVibrate(new long[]{1000,1000,1000,1000,1000})
                .setDefaults(Notification.DEFAULT_SOUND);


        Intent notificationIntent = new Intent(this, MyFirebaseMessagingService.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);


        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }



}
