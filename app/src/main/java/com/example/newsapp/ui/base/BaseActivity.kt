package com.example.newsapp.ui.base

import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.navigation.NavController
import com.example.newsapp.R

abstract class BaseActivity<T : ViewDataBinding?> : AppCompatActivity() {
    var viewDataBinding: T? = null
    @get:LayoutRes
    abstract val layoutId: Int
    protected abstract fun activityCreated()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding = DataBindingUtil.setContentView(this, layoutId)
        viewDataBinding?.executePendingBindings()
        activityCreated()
    }

    fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    fun makeToast(message:String , length:Int){
        Toast.makeText(baseContext, message , length).show()
    }
    public override fun onDestroy() {
        super.onDestroy()
        viewDataBinding?.unbind()
        viewDataBinding = null
    }
}