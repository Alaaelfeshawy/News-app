package com.example.newsapp

import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.newsapp.base.BaseActivity
import com.example.newsapp.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView


class MainActivity : BaseActivity<ActivityMainBinding>()
    ,NavigationView.OnNavigationItemSelectedListener
{
    private  var binding: ActivityMainBinding? = null
    var appBarConfiguration: AppBarConfiguration? = null
    override val layoutId: Int
        get() = R.layout.activity_main

    override fun activityCreated() {
        binding = viewDataBinding
        setSupportActionBar(binding?.toolbar)
        setTitle(R.string.app_name)
        appBarConfiguration = AppBarConfiguration.Builder(R.id.homeFragment)
            .setOpenableLayout(binding?.drawer)
            .build()
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment?
        val navController = navHostFragment?.navController
        navController?.let { controller ->
            appBarConfiguration?.let { appBarConfiguration ->
                NavigationUI.setupActionBarWithNavController(
                    this,
                    controller, appBarConfiguration
                )
            }
            binding?.navigationView?.let { navView ->
                NavigationUI.setupWithNavController(
                    navView,
                    navController
                )
            }
        }
        binding?.navigationView?.setNavigationItemSelectedListener(this)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return NavigationUI.navigateUp(navController,binding?.drawer)
    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_explore -> {
                makeToast(getString(R.string.explore), Toast.LENGTH_SHORT)
            }
            R.id.nav_gallery -> {
                makeToast(getString(R.string.gallery), Toast.LENGTH_SHORT)
            }
            R.id.nav_live_chat -> {
                makeToast(getString(R.string.live_chat), Toast.LENGTH_SHORT)
            }
            R.id.nav_wish_list -> {
                makeToast(getString(R.string.wish_list), Toast.LENGTH_SHORT)
            }
            R.id.nav_e_magazine -> {
                makeToast(getString(R.string.e_magazine), Toast.LENGTH_SHORT)
            }
        }
        binding?.drawer?.closeDrawer(GravityCompat.START)
        return true
    }
}