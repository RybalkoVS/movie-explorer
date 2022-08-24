package by.grsu.movieexplorer.gof.creational

import by.grsu.movieexplorer.gof.creational.abstractfactory.ComputerFactory
import by.grsu.movieexplorer.gof.creational.abstractfactory.DesktopFactory
import by.grsu.movieexplorer.gof.creational.abstractfactory.LaptopFactory
import by.grsu.movieexplorer.gof.creational.builder.DialogBuilder

class Sample {

    //abstract factory delegates creating similar groups of objects
    //to other factories
    //factory method - method to create objects
    //abstract factory - object to create groups(families) of objects
    fun abstractFactory() {
        val factory = ComputerFactory()

        val processor1 = factory.createProcessor(
            DesktopFactory(6, true)
        )

        val processor2 = factory.createProcessor(
            LaptopFactory(4, false)
        )
    }

    //creating same object with different characteristics needed in the concrete situation
    fun builder() {
        val dialog1 = DialogBuilder(title = "Title")
            .setMessage("Some message")
            .setPositiveButton("Ok") {}
            .build()

        val dialog2 = DialogBuilder(title = "Title")
            .setMessage("Some message")
            .setPositiveButton("Ok") {}
            .setNegativeButton("Cancel") {}
            .build()
    }
}