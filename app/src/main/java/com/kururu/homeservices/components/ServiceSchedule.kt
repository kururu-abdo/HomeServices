package com.kururu.homeservices.components

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.kururu.homeservices.MainActivity
import com.kururu.homeservices.utils.NOTIFICATION_ID

@SuppressLint("RestrictedApi")
class ServiceSchedule(context: Context,workerParameters: WorkerParameters) : Worker(context,workerParameters) {
    override fun doWork(): Result {
        showNotification()
        return Result.success()
    }

    private fun showNotification() {


        //intnet
        var intent =  Intent(
            applicationContext ,
            MainActivity::class.java
        ).apply {
            flags =  Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        //pending intnet

        var pendingIntnet =PendingIntent.getActivity(
            applicationContext,0 ,intent ,0
        )
        // notificaiton builder

        var builder =NotificationCompat.Builder(applicationContext,"my_channel")
            .setContentTitle("Home Service")
            .setContentText("Service is Running!")
            .setAutoCancel(true)
            .setContentIntent(pendingIntnet)

        //notifiy
        with(NotificationManagerCompat.from(applicationContext)

        ){
            notify(NOTIFICATION_ID ,builder.build())
        }





    }
}