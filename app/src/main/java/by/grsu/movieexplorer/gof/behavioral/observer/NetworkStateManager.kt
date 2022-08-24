package by.grsu.movieexplorer.gof.behavioral.observer

class NetworkStateManager {
    private val stateListeners: MutableList<NetworkStateListener> = mutableListOf()
    private var networkState: NetworkState = NetworkState.Disconnected

    fun registerListener(listener: NetworkStateListener) {
        stateListeners.add(listener)
    }

    fun unregisterListener(listener: NetworkStateListener) {
        stateListeners.remove(listener)
    }

    fun updateState(state: NetworkState) {
        networkState = state
        stateListeners.forEach { it.onStateChanged(state) }
    }
}

enum class NetworkState {
    Connected,
    Disconnected
}

interface NetworkStateListener {
    fun onStateChanged(state: NetworkState)
}