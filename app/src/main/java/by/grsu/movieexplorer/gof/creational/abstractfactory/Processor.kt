package by.grsu.movieexplorer.gof.creational.abstractfactory

interface Processor {
    val cores: Int
}

data class DesktopProcessor(
    override val cores: Int,
    val supportsOverclocking: Boolean
): Processor

data class LaptopProcessor(
    override val cores: Int,
    val isEnergyEfficient: Boolean
): Processor