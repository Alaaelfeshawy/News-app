package com.example.data.repository

open class BaseRepository(private val fromTest: Boolean = false) {
    val apiToken: String
        get() = if (!fromTest){
            "fe93f70a0f544c2fb245d45e3f346432"
        }else{
            "apiKey"
        }
}