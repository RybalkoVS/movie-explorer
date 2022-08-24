package by.grsu.movieexplorer.gof.behavioral.templatemethod

import android.util.Log
import java.io.File

abstract class FileUploaderTemplate {

    fun upload(filePath: String) {
        val file = getFile(filePath = filePath)

        if (file.exists()) {
            processExistingFile(file)
        } else {
            Log.e(FileUploaderTemplate::class.simpleName, "Show error")
        }
    }

    open fun getFile(filePath: String): File {
        return File(filePath)
    }

    private fun processExistingFile(file: File) {
        compressFile(file)

        if (isFileValid(file)) {
            sendFile(file)
        } else {
            Log.e(FileUploaderTemplate::class.simpleName, "Show error")
        }
    }

    open fun compressFile(file: File) {}

    //any file content/physical characteristics validation needed in specific situation
    abstract fun isFileValid(file: File): Boolean

    open fun sendFile(file: File) {
        //base realization
    }

}

class SampleFileUploader : FileUploaderTemplate() {

    override fun compressFile(file: File) {
        //additionally compress file
    }

    override fun isFileValid(file: File): Boolean {
        //validation for concrete case
        return true
    }

    override fun sendFile(file: File) {
        //send file to different source
    }
}

class SampleFileUploader1 : FileUploaderTemplate() {
    override fun getFile(filePath: String): File {
        //get file from non default data storage
        return File(filePath)
    }

    override fun isFileValid(file: File): Boolean {
        //validation for concrete case
        return true
    }
}



