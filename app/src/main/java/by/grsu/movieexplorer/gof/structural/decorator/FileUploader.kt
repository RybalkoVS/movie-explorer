package by.grsu.movieexplorer.gof.structural.decorator

import android.util.Log

interface FileUploader {

    fun uploadFile()
}

class FileUploaderImpl: FileUploader {

    override fun uploadFile() {
        Log.i(FileUploaderImpl::class.simpleName, "File uploaded")
    }
}