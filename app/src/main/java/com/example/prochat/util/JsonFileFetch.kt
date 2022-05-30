package com.example.prochat.util

import android.content.Context
import android.util.Log
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.nio.charset.Charset

class JsonFileFetch {
    fun getJSONFromAssets(context: Context,fileName:String): String? {
        val json: String?
        val charset: Charset = Charsets.UTF_8
        try {
            val jsonFile = context.assets.open(fileName)
            val size = jsonFile.available()
            val buffer = ByteArray(size)
            jsonFile.read(buffer)
            jsonFile.close()
            json = String(buffer, charset)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return json
    }

    fun saveJSONToAssets(context: Context,fileName:String,jsonString:String){
        try {
            Log.d("Hey", context.assets.locales[0])
            val file= File(context.filesDir,fileName)
            val fileWriter = FileWriter(file)
            val bufferedWriter = BufferedWriter(fileWriter)
            bufferedWriter.write(jsonString)
            bufferedWriter.close()
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
    }
}