package by.grsu.movieexplorer.gof.structural.composite

interface Product {

    fun getPrice(): Double
}

class Present(
    private val toys: List<Toy>
) : Product {
    override fun getPrice(): Double {
        var totalPrice = 0.0

        toys.forEach { toy ->
            totalPrice += toy.getPrice()
        }
        return totalPrice
    }
}

open class Toy(
    private val price: Double
) : Product {

    override fun getPrice(): Double {
        return price
    }
}

class DiscountedToy(
    private val price: Double,
    private val discountPercent: Double
) : Toy(price) {

    override fun getPrice(): Double {
        return price * (discountPercent / 100)
    }
}