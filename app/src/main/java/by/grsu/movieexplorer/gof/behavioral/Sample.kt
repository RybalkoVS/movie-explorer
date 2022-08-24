package by.grsu.movieexplorer.gof.behavioral

import by.grsu.movieexplorer.gof.behavioral.observer.NetworkState
import by.grsu.movieexplorer.gof.behavioral.observer.NetworkStateManager
import by.grsu.movieexplorer.gof.behavioral.observer.Subscriber
import by.grsu.movieexplorer.gof.behavioral.observer.Subscriber1
import by.grsu.movieexplorer.gof.behavioral.templatemethod.SampleFileUploader
import by.grsu.movieexplorer.gof.behavioral.templatemethod.SampleFileUploader1

class Sample {
    fun observer() {
        val networkManager = NetworkStateManager()
        val subscriber = Subscriber()
        val subscriber1 = Subscriber1()

        networkManager.registerListener(subscriber)
        networkManager.registerListener(subscriber1)

        networkManager.updateState(NetworkState.Disconnected) //all subscribers notified and receive data

        networkManager.unregisterListener(subscriber) //don't need to observe state anymore
    }

    fun templateMethod() {
        val filepath = "Dummy filepath"

        val sampleFileUploader = SampleFileUploader()
        val sampleFileUploader1 = SampleFileUploader1()

        //processing same file upload algorithm provided by base uploader(template)
        //overrides some different steps of algorithm
        //to provide some unique logic to this steps
        //but not changing whole algorithm
        sampleFileUploader.upload(filepath)
        sampleFileUploader1.upload(filepath)
    }
}