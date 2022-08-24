package by.grsu.movieexplorer.gof.structural

import by.grsu.movieexplorer.gof.structural.composite.DiscountedToy
import by.grsu.movieexplorer.gof.structural.composite.Present
import by.grsu.movieexplorer.gof.structural.composite.ProductComposite
import by.grsu.movieexplorer.gof.structural.composite.Toy
import by.grsu.movieexplorer.gof.structural.decorator.CompressFileUploaderDecorator
import by.grsu.movieexplorer.gof.structural.decorator.FileUploaderImpl

class Sample {

    //useful when we could build a tree structure of many components because of common properties
    //and nesting of this objects
    //each component can contain unit or a list of the same or closely the same component
    //avoids us of iterating by the whole tree
    //when we need to get some of components common properties
    //by delegating it to inner components through common interface
    fun composite() {
        val productComposite = ProductComposite()

        productComposite.addProduct(Present(
            listOf(
                Toy(2.5),
                DiscountedToy(1.5, 20.5)
            )
        ))

        productComposite.getPrice() //get price of every object inside tree hierarchy
    }


    //allows us to add some external logic to some operation without changing default implementation
    //by wrapping default logic in a new class with some extensions of the same logic
    fun decorator() {
        val fileUploader = FileUploaderImpl()
        val decoratorUploader = CompressFileUploaderDecorator(fileUploader)

        //default method
        fileUploader.uploadFile()

        //same method but with extended logic described inside decorator class
        //default logic is not broken or changed
        //extended logic invoked just after or before default
        decoratorUploader.uploadFile()
    }
}