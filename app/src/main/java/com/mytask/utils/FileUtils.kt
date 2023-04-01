package com.mytask.utils

import android.content.Context
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.util.*

class FileUtils(private val context: Context) {
    fun copyRawFileToInternalStorage(resourceId: Int): String {
        val inputStream: InputStream = context.resources.openRawResource(resourceId)
        val filePath = context.filesDir.absolutePath + "/" + resourceId
        val outputStream: FileOutputStream
        try {
            outputStream = FileOutputStream(filePath)
            val buffer = ByteArray(1024)
            var length: Int
            while (inputStream.read(buffer).also { length = it } > 0) {
                outputStream.write(buffer, 0, length)
            }
            inputStream.close()
            outputStream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return filePath
    }

    fun getConcatenatedStackAudioFilePath(stackNo: Int): String {
        return context.filesDir.absolutePath + "/concatenated_audio$stackNo.mp3"
    }

    fun getConcatenatedAudioFilePath(time: Long): String {
        return context.filesDir.absolutePath + "/concatenated_audio$time.mp3"
    }
}