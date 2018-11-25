package org.sopt.kclean.Firebase;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONException;
import org.json.JSONObject;
import org.sopt.kclean.R;
import org.sopt.kclean.View.*;

import java.util.Map;

/**
     * Copyright 2016 Google Inc. All Rights Reserved.
     *
     * Licensed under the Apache License, Version 2.0 (the "License");
     * you may not use this file except in compliance with the License.
     * You may obtain a copy of the License at
     *
     * http://www.apache.org/licenses/LICENSE-2.0
     *
     * Unless required by applicable law or agreed to in writing, software
     * distributed under the License is distributed on an "AS IS" BASIS,
     * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
     * See the License for the specific language governing permissions and
     * limitations under the License.
     */

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    @Override
    public void handleIntent(Intent intent) {
   //     super.handleIntent(intent);
//        Intent intent2 = new Intent(getApplicationContext(), SplashActivity.class);
//        Bundle bundle = new Bundle();
//        bundle.putInt("result", 1);
//        intent2.putExtras(bundle);
//        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 1, intent2, PendingIntent.FLAG_CANCEL_CURRENT);
//        try {
//            pendingIntent.send();
//        } catch (PendingIntent.CanceledException e) {
//            e.printStackTrace();
//        }

        try
        {
            if (intent.getExtras() != null)
            {
                RemoteMessage.Builder builder = new RemoteMessage.Builder("MyFirebaseMessagingService");

                for (String key : intent.getExtras().keySet())
                {
                    builder.addData(key, intent.getExtras().get(key).toString());
                }

                onMessageReceived(builder.build());
            }
            else
            {
                super.handleIntent(intent);
            }
        }
        catch (Exception e)
        {
            super.handleIntent(intent);
        }
    }

    private static final String TAG = "MyFirebaseMsgService";

    /**
     * Called when message is received.
     *
     * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
     */
    // [START receive_message]
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // [START_EXCLUDE]
        // There are two types of messages data messages and notification messages. Data messages are handled
        // here in onMessageReceived whether the app is in the foreground or background. Data messages are the type
        // traditionally used with GCM. Notification messages are only received here in onMessageReceived when the app
        // is in the foreground. When the app is in the background an automatically generated notification is displayed.
        // When the user taps on the notification they are returned to the app. Messages containing both notification
        // and data payloads are treated as notification messages. The Firebase console always sends notification
        // messages. For more see: https://firebase.google.com/docs/cloud-messaging/concept-options
        // [END_EXCLUDE]

        Log.d("ok",remoteMessage.getNotification().getBody());
        System.out.println(remoteMessage.getNotification());
        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        // Check if message contains a data payload.

//            Log.d(TAG, "OKOKOK" + remoteMessage.getNotification().getBody() + "data size : " + remoteMessage.getNotification().getBody().toString());
//            if (!remoteMessage.getNotification().getBody().isEmpty()) {
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());


//                if (/* Check if data needs to be processed by long running job */ true) {
//                    // For long-running tasks (10 seconds or more) use Firebase Job Dispatcher.
//               //     scheduleJob();
//                } else {
//                    // Handle message within 10 seconds
//                    handleNow();
//                }
            //String click_action =  remoteMessage.getNotification().getClickAction();
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(remoteMessage.getData());
            sendNotification(remoteMessage.getData(),jsonObject.getString("result"),remoteMessage.getNotification());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
    }
    //   [END receive_message]

    /**
     * Schedule a job using FirebaseJobDispatcher.
     */
//        private void scheduleJob() {
//            // [START dispatch_job]
//            FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(new GooglePlayDriver(this));
//            Job myJob = dispatcher.newJobBuilder()
//                    .setService(MyJobService.class)
//                    .setTag("my-job-tag")
//                    .build();
//            dispatcher.schedule(myJob);
//            // [END dispatch_job]
//        }

    /**
     * Handle time allotted to BroadcastReceivers.
     */
    private void handleNow() {
        Log.d(TAG, "Short lived task is done.");
    }

    /**
     * Create and show a simple notification containing the received FCM message.
     *
     * @param messageBody FCM message body received.
     */
    private void sendNotification(Map<String,String> messageBody,String data,RemoteMessage.Notification noti) {
        Intent intent = new Intent(this, SplashActivity.class);

        Bundle bundle = new Bundle();
        bundle.putInt("result", 1);
        bundle.putString("notice_id",data);
        intent.putExtras(bundle);
        intent.putExtra("result", 1);
        intent.putExtra("notice_id",data);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0 /* Request code */, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        String channelId = getString(R.string.default_notification_channel_id);
        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this,channelId)
                        .setSmallIcon(R.drawable.sopt) //작은 아이콘 필수  지금 임시루 sopt이미지 해놓은거
                        .setContentTitle(noti.getTitle()) //제목 필수
                        .setContentText(noti.getBody()) //내용 필수
                        .setAutoCancel(true)
                        .setSound(defaultSoundUri)
                        .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
        Log.d("1234",""+1234);
//            startActivity(intent);

    }
}



