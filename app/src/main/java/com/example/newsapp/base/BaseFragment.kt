package com.example.newsapp.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation

abstract class BaseFragment<T : ViewDataBinding?> : Fragment() {
    var baseActivity: BaseActivity<*>? = null

    var viewDataBinding: T? = null

    @JvmField
    protected var navController: NavController? = null

    @get:LayoutRes
    abstract val layoutId: Int
    protected abstract fun viewSetup()
    protected abstract fun viewModelSetup()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity<*>) {
            baseActivity = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return viewDataBinding?.root
    }

    override fun onDetach() {
        baseActivity = null
        super.onDetach()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDataBinding?.lifecycleOwner = this
        viewDataBinding?.executePendingBindings()
        navController = Navigation.findNavController(view)
        viewModelSetup()
        viewSetup()
    }

    fun hideKeyboard() {
        baseActivity?.hideKeyboard()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewDataBinding?.unbind()
        viewDataBinding = null
    }


}