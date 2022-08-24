package by.grsu.movieexplorer.gof.behavioral.observer

import android.util.Log

class Subscriber : NetworkStateListener {
    var text = ""

    override fun onStateChanged(state: NetworkState) {
        text = state.toString()
        Log.i(Subscriber::class.simpleName, text)
    }
}

class Subscriber1 : NetworkStateListener {

    override fun onStateChanged(state: NetworkState) {
        when (state) {
            NetworkState.Disconnected -> {
                showError()
            }
            NetworkState.Connected -> {
                showSuccessNotification()
                //do smthg
            }
        }
    }

    private fun showSuccessNotification() {
        Log.i(Subscriber::class.simpleName, "Success")
    }
    private fun showError() {
        Log.i(Subscriber::class.simpleName, "Error")
    }
}