package by.grsu.movieexplorer.gof.creational.abstractfactory


interface AbstractComputerFactory {

    //familiar groups of objects to create
    fun createProcessor(): Processor
//    fun createMotherBoard(): Motherboard
//    fun createRam(): Ram

}

class ComputerFactory {

    fun createProcessor(abstractComputerFactory: AbstractComputerFactory): Processor {
        return abstractComputerFactory.createProcessor()
    }
}

class DesktopFactory(
    private val cores: Int,
    private val supportsOverclocking: Boolean
) : AbstractComputerFactory {

    override fun createProcessor(): Processor {
        return DesktopProcessor(cores, supportsOverclocking)
    }
}

class LaptopFactory(
    private val cores: Int,
    private val isEnergyEfficient: Boolean
) : AbstractComputerFactory {

    override fun createProcessor(): Processor {
        return LaptopProcessor(cores, isEnergyEfficient)
    }
}