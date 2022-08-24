package by.grsu.movieexplorer.movies.presentation.fragment

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import by.grsu.movieexplorer.R
import by.grsu.movieexplorer.movies.presentation.service.SampleService

class FavouritesFragment : Fragment(R.layout.fragment_favourites) {

    companion object {
        const val INTENT_ACTION = "service finished work"
        const val PROGRESS_VALUE = "progress"
    }

    private var runServiceButton: Button? = null
    private var progressText: TextView? = null
    private val sampleBroadcastReceiver = object : BroadcastReceiver() {

        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent?.action == INTENT_ACTION) {
                progressText?.text = intent.getIntExtra(PROGRESS_VALUE, 0).toString()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)
    }

    override fun onStart() {
        super.onStart()

        context?.registerReceiver(sampleBroadcastReceiver, IntentFilter(INTENT_ACTION))
    }

    private fun initViews(rootView: View) {
        runServiceButton = rootView.findViewById<Button?>(R.id.button_run_service)?.also {
            it.setOnClickListener {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    context?.startForegroundService(Intent(context, SampleService::class.java))
                }
            }
        }
        progressText = rootView.findViewById(R.id.progress_value_text)
    }

    override fun onStop() {
        super.onStop()

        context?.unregisterReceiver(sampleBroadcastReceiver)
    }

    override fun onDestroy() {
        progressText = null
        runServiceButton = null
        context?.stopService(Intent(context, SampleService::class.java))
        super.onDestroy()
    }
}