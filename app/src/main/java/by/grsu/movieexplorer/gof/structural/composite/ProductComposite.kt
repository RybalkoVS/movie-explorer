package by.grsu.movieexplorer.gof.structural.composite

class ProductComposite: Product {
    private val products: MutableList<Product> = mutableListOf()

    fun addProduct(product: Product) {
        products.add(product)
    }

    fun removeProduct(product: Product) {
        products.remove(product)
    }

    override fun getPrice(): Double {
        var totalPrice = 0.0

        products.forEach { product ->
            totalPrice += product.getPrice()
        }
        return totalPrice
    }
}