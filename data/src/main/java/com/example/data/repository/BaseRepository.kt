package com.example.data.repository

open class BaseRepository(private val fromTest: Boolean = false) {
    val apiToken: String
        get() = if (!fromTest){
            "533af958594143758318137469b41ba9"
        }else{
            "apiKey"
        }
}