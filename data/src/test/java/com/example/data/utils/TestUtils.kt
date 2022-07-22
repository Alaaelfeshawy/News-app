package com.example.data.utils


import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.mockito.ArgumentCaptor

object TestUtils {
    fun getJson(fileName: String): String {
        val inputStream = javaClass.classLoader?.getResourceAsStream(fileName)

        val out = StringBuilder()
        inputStream?.bufferedReader()?.useLines { lines ->
            lines.forEach { line ->
                out.append(line)
            }
        }

        return out.toString()
    }

    fun <T> getEntityFromJson(json: String, className: Class<T>, ): T? {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        val jsonAdapter: JsonAdapter<T>? =
            moshi.adapter(className)

        return jsonAdapter?.fromJson(json)
    }

    fun <T> capture(argumentCaptor: ArgumentCaptor<T>): T = argumentCaptor.capture()

}