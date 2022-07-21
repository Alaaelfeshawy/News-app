package com.example.data.source.local.room

import com.example.data.source.local.room.dao.article.ArticleDao
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.data.source.local.room.entity.home.ArticleData
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.io.IOException
import java.lang.reflect.Type

@Database(
    entities = [ArticleData::class],
    exportSchema = false,
    version = 1
)
@TypeConverters(
    AppDatabase.Converters::class
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao
    class Converters {
        @TypeConverter
        fun stringListToJson(strList: List<String>): String {
            val moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()
            val strListType: Type = Types.newParameterizedType(
                MutableList::class.java, String::class.java
            )
            val jsonAdapter = moshi.adapter<List<String>>(strListType)
            return jsonAdapter.toJson(strList)
        }

        @TypeConverter
        fun jsonToStringList(json: String?): List<String>? {
            val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory())
                .build()
            val strListType: Type = Types.newParameterizedType(
                MutableList::class.java, String::class.java
            )
            val jsonAdapter = moshi.adapter<List<String>>(strListType)
            var strList: List<String>? = null
            try {
                strList = jsonAdapter.fromJson(json!!)
            } catch (e: IOException) {
                e.printStackTrace()
            }
            return strList
        }
    }
}