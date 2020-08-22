package com.hoony.firebasemessageexample.service

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.hoony.firebasemessageexample.notification.NotificationHelper

class MyFirebaseMessagingService : FirebaseMessagingService() {

    companion object {
        private const val TAG = "hoony"

        const val PUSH_ID = "id"
        const val PUSH_ACTION_TYPE = "to"
        private const val PUSH_TICKER = "ticker" //status바에 띄워지는 메세지
        const val PUSH_TITLE = "title"
        const val PUSH_MESSAGE = "message"
        private const val PUSH_IMAGE_URL = "url"
        private const val PUSH_BIG_PICTURE_URL = "big_picture_url"
        private const val PUSH_BIG_TEXT = "big_text"
        private const val PUSH_EXTRA_BUTTON_TITLE = "title"
        private const val PUSH_WEBVIEW_URL = "webview_url"
        private const val PUSH_ACTION_BUTTON_1 = "action_button1"
        private const val PUSH_ACTION_BUTTON_2 = "action_button2"
        private const val PUSH_ICON = "icon"
        private const val PUSH_OPTION = "opt"
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
//        val title: String = remoteMessage.notification?.title ?: "No title"
//        val message: String = remoteMessage.notification?.body ?: "No body"

        val title: String = remoteMessage.data[PUSH_TITLE] ?: "No title"
        val message: String = remoteMessage.data[PUSH_MESSAGE] ?: "No message"

        NotificationHelper.sendNotification(this@MyFirebaseMessagingService, title, message)

        // ...

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "From: ${remoteMessage.from}")

        // Check if message contains a data payload.
        if (remoteMessage.data.isNotEmpty()) {
            Log.d(TAG, "Message data payload: ${remoteMessage.data}")

            if (/* Check if data needs to be processed by long running job */ true) {
                // For long-running tasks (10 seconds or more) use WorkManager.
//                scheduleJob()
            } else {
                // Handle message within 10 seconds
//                handleNow()
            }
        }

        // Check if message contains a notification payload.
        remoteMessage.notification?.let {
            Log.d(TAG, "Message Notification Body: ${it.body}")
        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
    }
}