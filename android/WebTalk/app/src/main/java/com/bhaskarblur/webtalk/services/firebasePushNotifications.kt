package com.bhaskarblur.webtalk.services

import android.R
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Build
import android.preference.PreferenceManager
import android.util.Log
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.bhaskarblur.webtalk.ui.mainActivity
import com.bhaskarblur.webtalk.utils.firebaseHandler
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


class firebasePushNotifications : FirebaseMessagingService() {

  override fun onNewToken(token: String) {
   Log.d(TAG, "Refreshed token: $token")

   // If you want to send messages to this application instance or
   // manage this apps subscriptions on the server side, send the
   // FCM registration token to your app server.

   var sharedPreferences : SharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
   var user = sharedPreferences.getString("userEmail", "");
   var isLogged = sharedPreferences.getBoolean("loggedStatus", false);

   if(isLogged) {
    firebaseHandler().updateNotification(
     token, this,
     FirebaseDatabase.getInstance("https://webtalk-72d64-default-rtdb.asia-southeast1.firebasedatabase.app/")
      .getReference("Users"), user.toString()
    )
   }
  }

 override fun onMessageReceived(remoteMessage: RemoteMessage) {
  // First case when notifications are received via
  // data event
  // Here, 'title' and 'message' are the assumed names
  // of JSON
  // attributes. Since here we do not have any data
  // payload, This section is commented out. It is
  // here only for reference purposes.
  /*if(remoteMessage.getData().size()>0){
            showNotification(remoteMessage.getData().get("title"),
                          remoteMessage.getData().get("message"));
        }*/

  // Second case when notification payload is
  // received.
  if (remoteMessage.notification != null) {
   // Since the notification is received directly
   // from FCM, the title and the body can be
   // fetched directly as below.
   showNotification(
    remoteMessage.notification!!.title,
    remoteMessage.notification!!.body
   )
  }
 }

 // Method to get the custom Design for the display of
 // notification.

 // Method to display the notifications
 fun showNotification(
  title: String?,
  message: String?
 ) {
  // Pass the intent to switch to the MainActivity
  val intent = Intent(this, mainActivity::class.java)
  // Assign channel ID
  val channel_id = "notification_channel"
  // Here FLAG_ACTIVITY_CLEAR_TOP flag is set to clear
  // the activities present in the activity stack,
  // on the top of the Activity that is to be launched
  intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
  // Pass the intent to PendingIntent to start the
  // next Activity
  val pendingIntent = PendingIntent.getActivity(
   this, 0, intent,
   PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE
  )

  // Create a Builder object using NotificationCompat
  // class. This will allow control over all the flags
  var builder: NotificationCompat.Builder = NotificationCompat.Builder(
   applicationContext,
   channel_id
  )
   .setSmallIcon(R.drawable.presence_video_online)
   .setAutoCancel(true)
   .setVibrate(
    longArrayOf(
     1000, 1000, 1000,
     1000, 1000
    )
   )
   .setOnlyAlertOnce(true)
   .setContentIntent(pendingIntent)

  // A customized design for the notification can be
  // set only for Android versions 4.1 and above. Thus
  // condition for the same is checked here.
  if (Build.VERSION.SDK_INT
   >= Build.VERSION_CODES.JELLY_BEAN
  ) {
   builder = builder.setContentTitle(title)
    .setContentText(message)
    .setSmallIcon(R.drawable.presence_video_online)
  } // If Android Version is lower than Jelly Beans,
  else {
   builder = builder.setContentTitle(title)
    .setContentText(message)
    .setSmallIcon(R.drawable.presence_video_online)
    .setSound(Uri.parse("https://res.cloudinary.com/dsnb1bl19/video/upload/v1695660666/slssaapl93zuq7cuocg7.wav"))
  }

  val notificationManager = getSystemService(
   Context.NOTIFICATION_SERVICE
  ) as NotificationManager
  // Check if the Android Version is greater than Oreo
  if (Build.VERSION.SDK_INT
   >= Build.VERSION_CODES.O
  ) {
   val notificationChannel = NotificationChannel(
    channel_id, "call",
    NotificationManager.IMPORTANCE_HIGH
   )
   notificationManager.createNotificationChannel(
    notificationChannel
   )
  }
  notificationManager.notify(0, builder.build())
 }
}