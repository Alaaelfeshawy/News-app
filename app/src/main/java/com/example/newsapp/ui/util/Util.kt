package com.example.newsapp.ui.util

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AlertDialog
import com.example.newsapp.R
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


object Util {
    var alert: AlertDialog? = null

    fun showLoading(context: Context) {
        val builder = AlertDialog.Builder(context)
        builder.setView(R.layout.progress_bar_dialog)
        builder.setCancelable(false)
        alert = builder.create()
        if (alert?.window != null) {
            alert?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
        alert?.show()
    }

    fun dismissLoading() {
        if (alert != null) {
            alert?.dismiss()
        }
    }

    fun covertDate(date:String) : String {
        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
        val dateTime: LocalDateTime = LocalDateTime.parse(date, formatter)
        val formatter2: DateTimeFormatter = DateTimeFormatter.ofPattern("MMMM dd,yyyy")
        return dateTime.format(formatter2)
    }
}