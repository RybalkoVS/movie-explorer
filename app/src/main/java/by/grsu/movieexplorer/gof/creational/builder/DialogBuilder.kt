package by.grsu.movieexplorer.gof.creational.builder


class DialogBuilder(private val title: String) {

    private var message: String? = null
    private var negativeButton: Button? = null
    private var positiveButton: Button? = null

    fun setMessage(message: String): DialogBuilder {
        this.message = message
        return this
    }

    fun setPositiveButton(text: String, action: () -> Unit): DialogBuilder {
        positiveButton = Button(text, action)
        return this
    }

    fun setNegativeButton(text: String, action: () -> Unit): DialogBuilder {
        negativeButton = Button(text, action)
        return this
    }

    fun build(): Dialog {
        return Dialog(title, message, positiveButton, negativeButton)
    }
}