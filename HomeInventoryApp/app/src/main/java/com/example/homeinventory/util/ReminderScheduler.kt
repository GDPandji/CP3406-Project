package com.example.homeinventory.util

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Settings
import android.widget.Toast
import com.example.homeinventory.util.ReminderReceiver

object ReminderScheduler {
    fun scheduleReminder(context: Context, triggerTimeMillis: Long, title: String, message: String) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        // Android 12+ (API 31): Check if exact alarms are allowed
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            if (!alarmManager.canScheduleExactAlarms()) {
                Toast.makeText(
                    context,
                    "Exact alarms not allowed. Enable in settings.",
                    Toast.LENGTH_LONG
                ).show()

                // Open system settings for the user to enable it
                val intent = Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM)
                context.startActivity(intent)
                return
            }
        }

        val intent = Intent(context, ReminderReceiver::class.java).apply {
            putExtra("TITLE", title)
            putExtra("MESSAGE", message)
        }

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            triggerTimeMillis.toInt(), // unique ID
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        alarmManager.setExact(
            AlarmManager.RTC_WAKEUP,
            triggerTimeMillis,
            pendingIntent
        )
    }
}
