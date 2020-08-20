package com.hoony.firebasemessageexample.service

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.hoony.firebasemessageexample.notification.NotificationHelper

class MyFirebaseMessagingService : FirebaseMessagingService() {

    companion object {
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
        val title: String = remoteMessage.notification?.title ?: "No title"
        val message: String = remoteMessage.notification?.body ?: "No body"

        NotificationHelper.sendNotification(this@MyFirebaseMessagingService, title, message)
    }
}