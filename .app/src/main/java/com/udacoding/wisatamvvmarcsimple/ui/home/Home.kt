package com.udacoding.wisatamvvmarcsimple.ui.home

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.view.Menu
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import androidx.navigation.findNavController
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import androidx.navigation.ui.*
import com.udacoding.wisatamvvmarcsimple.R
import com.udacoding.wisatamvvmarcsimple.databinding.ActivityHomeBinding
import com.udacoding.wisatamvvmarcsimple.ui.home.ui.gallery.GalleryFragment
import com.udacoding.wisatamvvmarcsimple.ui.home.ui.home.HomeFragment
import com.udacoding.wisatamvvmarcsimple.ui.home.ui.slideshow.SlideshowFragment
import androidx.navigation.ui.NavigationUI




class Home : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener, NavigationView.OnNavigationItemSelectedListener {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityHomeBinding
    private lateinit var  toggle : ActionBarDrawerToggle


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarHome.toolbar)
       /* Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
            .setAction("Action", null).show()
        */

        toggle = ActionBarDrawerToggle(
            this, binding.drawerLayout, binding.appBarHome.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        binding.navView.setNavigationItemSelectedListener(this)
        binding.btnMain.setOnNavigationItemSelectedListener(this)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_home)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
    private fun loadFragment(fragment: Fragment?): Boolean {
        if (fragment != null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment_content_home, fragment)
                .commit()
            return true
        }
        return false
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        for (fragment in supportFragmentManager.fragments) {
            supportFragmentManager.beginTransaction().remove(fragment!!).commit()
        }
        var fragment: Fragment? = null
        when (item.itemId) {
            R.id.home_menu -> fragment = HomeFragment()
            R.id.search_menu -> fragment = GalleryFragment()
            R.id.favorite_menu -> fragment = SlideshowFragment()
            R.id.account_menu -> {
                binding.drawerLayout.openDrawer(GravityCompat.START)
                return false
            }
        }
        binding.drawerLayout.closeDrawer(GravityCompat.START);
        return loadFragment(fragment)
    }
}