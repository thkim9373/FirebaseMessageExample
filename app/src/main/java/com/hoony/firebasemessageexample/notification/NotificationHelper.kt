package com.hoony.firebasemessageexample.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.TaskStackBuilder
import com.hoony.firebasemessageexample.MainActivity
import com.hoony.firebasemessageexample.R

class NotificationHelper {

    companion object {
        private const val NOTIFICATION_ID: String = "DEFAULT_NOTIFICATION_ID"
        private const val NOTIFICATION_NAME: String = "Default notification"
        private const val NOTIFICATION_DESCRIPTION: String = "Description"

        fun sendNotification(context: Context, title: String, message: String) {
            val notificationManager: NotificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            createNotificationChannel(notificationManager)
            val notification = buildNotification(context, title, message)

            notificationManager.notify(0, notification)
        }

        private fun createNotificationChannel(
            notificationManager: NotificationManager
        ) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                val notificationChannel = NotificationChannel(
                    NOTIFICATION_ID,
                    NOTIFICATION_NAME,
                    NotificationManager.IMPORTANCE_DEFAULT
                ).apply {
                    description = NOTIFICATION_DESCRIPTION
                    enableLights(true)
                    enableVibration(true)
                    vibrationPattern = longArrayOf(100, 200, 100, 200)
                    lockscreenVisibility = Notification.VISIBILITY_PUBLIC
                }

                notificationManager.createNotificationChannel(notificationChannel)
            }
        }

        private fun buildNotification(
            context: Context,
            title: String,
            message: String
        ): Notification {
            val notificationBuilder = NotificationCompat.Builder(context, NOTIFICATION_ID)
                .apply {
                    setSmallIcon(R.drawable.ic_launcher_background)
                    setAutoCancel(true)
                    setDefaults(Notification.DEFAULT_ALL)
                    setWhen(System.currentTimeMillis())
                    setContentTitle(title)
                    setContentText(message)
                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
                        setVibrate(longArrayOf(500, 500))
                    }
                }
            return notificationBuilder.build()
        }

        private fun getPendingIntent(context: Context): PendingIntent {
            // Create an Intent for the activity you want to start
            val intent = Intent(context, MainActivity::class.java)
            // Create the TaskStackBuilder
            val mainPendingIntent: PendingIntent? = TaskStackBuilder.create(context).run {
                // Add the intent, which inflates the back stack
                addNextIntentWithParentStack(intent)
                // Get the PendingIntent containing the entire back stack
                getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
            }

            val pendingIntent = PendingIntent.getService(context, 0, intent, 0)

            return pendingIntent
        }
    }
}