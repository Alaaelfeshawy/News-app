package com.example.newsapp

import androidx.navigation.fragment.NavHostFragment
import com.example.newsapp.base.BaseActivity
import com.example.newsapp.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {

    private var binding: ActivityMainBinding? = null

    override val layoutId: Int
        get() = R.layout.activity_main

    override fun activityCreated() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
    }


}