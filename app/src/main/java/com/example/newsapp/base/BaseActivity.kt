package com.example.newsapp.base

import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.navigation.NavController

abstract class BaseActivity<T : ViewDataBinding?> : AppCompatActivity() {
    var viewDataBinding: T? = null
        private set
    @JvmField
    protected var navController: NavController? = null

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

    public override fun onDestroy() {
        super.onDestroy()
        viewDataBinding?.unbind()
        viewDataBinding = null
    }
}