package com.example.newsapp

import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.newsapp.base.BaseActivity
import com.example.newsapp.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView


class MainActivity : BaseActivity<ActivityMainBinding>()
//    NavigationView.OnNavigationItemSelectedListener
{

    private lateinit var binding: ActivityMainBinding

//    var appBarConfiguration: AppBarConfiguration? = null

    override val layoutId: Int
        get() = R.layout.activity_main

    override fun activityCreated() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
//        setSupportActionBar(binding?.toolbar)
//        setTitle(R.string.app_name)
//        appBarConfiguration = AppBarConfiguration.Builder(R.id.homeFragment)
//            .setOpenableLayout(binding?.drawer)
//            .build()
//        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment?
//        navController = navHostFragment?.navController
//        navController?.let { navController ->
//            appBarConfiguration?.let { appBarConfiguration ->
//                NavigationUI.setupActionBarWithNavController(
//                    this,
//                    navController, appBarConfiguration
//                )
//            }
//            binding?.navigationView?.let { navView ->
//                NavigationUI.setupWithNavController(
//                    navView,
//                    navController
//                )
//            }
        }
//        binding?.navigationView?.setNavigationItemSelectedListener(this)
    }

//    override fun onSupportNavigateUp(): Boolean {
//            if (navController == null || appBarConfiguration == null)
//                super.onSupportNavigateUp()
//            return NavigationUI.navigateUp(navController!!, appBarConfiguration!!)
//                    || super.onSupportNavigateUp()
//    }


//    override fun onNavigationItemSelected(item: MenuItem): Boolean {
//        val id = item.itemId
//        when (id) {
//            R.id.nav_explore -> {
//              Toast.makeText(this,R.string.explore,Toast.LENGTH_LONG)
//                Log.d("HHHI", "onNavigationItemSelected: clicked explore")
//            }
//            R.id.nav_gallery -> {
//                Toast.makeText(this,R.string.gallery,Toast.LENGTH_SHORT)
//            }
//            R.id.nav_live_chat -> {
//                Toast.makeText(this,R.string.live_chat,Toast.LENGTH_SHORT)
//            }
//            R.id.nav_wish_list -> {
//                Toast.makeText(this,R.string.wish_list,Toast.LENGTH_SHORT)
//            }
//            R.id.nav_e_magazine -> {
//                Toast.makeText(this,R.string.e_magazine,Toast.LENGTH_SHORT)
//            }
//        }
//        binding?.drawer?.closeDrawer(GravityCompat.START)
//        return true
//    }

//}