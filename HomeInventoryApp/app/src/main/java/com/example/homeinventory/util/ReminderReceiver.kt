package com.example.homeinventory.util

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.graphics.Color
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.homeinventory.MainActivity
import com.example.homeinventory.R

class ReminderReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val itemName = intent.getStringExtra("itemName") ?: "Item Reminder"

        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channelId = "reminder_channel"

        // Create notification channel if needed (Android 8.0+)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Reminders",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Notifications for item reminders"
                enableLights(true)
                lightColor = Color.RED
            }
            notificationManager.createNotificationChannel(channel)
        }

        val intentToLaunch = Intent(context, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            context, 0, intentToLaunch,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val notification = NotificationCompat.Builder(context, channelId)
            .setContentTitle("Reminder")
            .setContentText("Don't forget: $itemName")
            .setSmallIcon(R.drawable.ic_launcher_foreground) // replace if you want custom icon
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        notificationManager.notify(System.currentTimeMillis().toInt(), notification)
    }
}
