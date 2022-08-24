package by.grsu.movieexplorer.movies.presentation.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Binder
import android.os.Build
import android.os.IBinder
import androidx.annotation.RequiresApi
import by.grsu.movieexplorer.R
import by.grsu.movieexplorer.movies.presentation.fragment.FavouritesFragment.Companion.INTENT_ACTION
import by.grsu.movieexplorer.movies.presentation.fragment.FavouritesFragment.Companion.PROGRESS_VALUE
import kotlinx.coroutines.*

private const val NOTIFICATION_ID = 1
private const val CHANNEL_ID = "SampleService"

class SampleService : Service() {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    private val sampleServiceBinder = SampleServiceBinder()

    override fun onBind(p0: Intent?): IBinder {
        return sampleServiceBinder
    }

    override fun onCreate() {
        showNotification()
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        startLongOperation()
        return START_NOT_STICKY
    }

    private fun showNotification() {
        val notification: Notification
        val channelId: String

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            channelId = createNotificationChannel()
            notification = Notification.Builder(this, channelId)
                .setContentText("Running...")
                .setSmallIcon(R.drawable.ic_favourites)
                .build()
        } else {
            TODO()
        }

        startForeground(NOTIFICATION_ID, notification)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(): String {
        val channel = NotificationChannel(
            CHANNEL_ID,
            "Sample service channel",
            NotificationManager.IMPORTANCE_NONE
        )

        val service = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        service.createNotificationChannel(channel)
        return CHANNEL_ID
    }

    private fun startLongOperation() {
        coroutineScope.launch {
            var progress = 0

            for (i in 0 until 10) {
                delay(1000)
                progress += 1
                withContext(Dispatchers.Main) {
                    sendBroadcast(Intent(INTENT_ACTION).apply {
                        putExtra(PROGRESS_VALUE, progress)
                    })
                }
            }
            stopSelf()
        }
    }

    override fun onDestroy() {
        coroutineScope.cancel()

        super.onDestroy()
    }

    inner class SampleServiceBinder : Binder() {

        val service: SampleService
            get() = this@SampleService
    }
}