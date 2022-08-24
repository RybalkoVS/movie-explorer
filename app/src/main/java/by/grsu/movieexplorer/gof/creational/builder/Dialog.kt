package by.grsu.movieexplorer.gof.creational.builder

data class Dialog(
    val title: String,
    val message: String? = null,
    val positiveButton: Button? = null,
    val negativeButton: Button? = null
)

data class Button(
    val title: String,
    val onClick: () -> Unit
)