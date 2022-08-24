package by.grsu.movieexplorer.gof.structural.decorator

import android.util.Log

abstract class BaseFileUploaderDecorator(
    private val fileUploader: FileUploader
) : FileUploader {

    override fun uploadFile() {
        fileUploader.uploadFile()
    }
}

class CompressFileUploaderDecorator(fileUploader: FileUploader) :
    BaseFileUploaderDecorator(fileUploader) {

    override fun uploadFile() {
        compressFile()
        super.uploadFile()
    }

    fun compressFile() {
        Log.i(CompressFileUploaderDecorator::class.simpleName, "Compressed file")
    }
}