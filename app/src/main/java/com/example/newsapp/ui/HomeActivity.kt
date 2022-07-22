package com.example.newsapp.ui

import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.newsapp.R
import com.example.newsapp.ui.base.BaseActivity
import com.example.newsapp.databinding.ActivityMainBinding
import com.example.newsapp.ui.home.HomeViewModel
import com.example.newsapp.ui.news_details.NewsDetailsFragment
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityMainBinding>()
    ,NavigationView.OnNavigationItemSelectedListener
{
    private  var binding: ActivityMainBinding? = null
    private var appBarConfiguration: AppBarConfiguration? = null

    private val viewModel: HomeViewModel by lazy {
        ViewModelProvider(this)[HomeViewModel::class.java]
    }

    override val layoutId: Int
        get() = R.layout.activity_main

    override fun activityCreated() {
        binding = viewDataBinding
        setUpNavigation()
    }

    private fun setUpNavigation(){
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
        navController?.addOnDestinationChangedListener { controller, destination, arguments ->
            if (destination.id == R.id.webViewFragment
            ) {
                supportActionBar?.hide()
                binding?.toolbar?.visibility = View.GONE
            } else {
                binding?.toolbar?.visibility = View.VISIBLE
                setSupportActionBar(binding?.toolbar)
                supportActionBar?.show()
            }
        }
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.side_menu, menu)
        val searchItem: MenuItem? = menu.findItem(R.id.action_search)
        val searchView: SearchView = searchItem?.actionView as SearchView
        searchView.queryHint="Search"
        searchView.maxWidth = Integer.MAX_VALUE
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }
            override fun onQueryTextChange(s: String): Boolean {
                viewModel.search(s)
                return false
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_search -> {
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private val currentFragment: Fragment?
        get() {
            val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
            return navHostFragment?.childFragmentManager?.fragments?.get(0)
        }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        when (currentFragment) {
            is NewsDetailsFragment -> {
                menu.findItem(R.id.action_search).isVisible = false
            }
        }
        return super.onPrepareOptionsMenu(menu)
    }

}