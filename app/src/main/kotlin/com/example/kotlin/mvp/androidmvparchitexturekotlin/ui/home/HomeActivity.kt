package com.example.kotlin.mvp.androidmvparchitexturekotlin.ui.home

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.view.Menu
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import android.support.v7.app.ActionBarDrawerToggle
import android.view.LayoutInflater
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.kotlin.mvp.androidmvparchitexturekotlin.NewsApp
import com.example.kotlin.mvp.androidmvparchitexturekotlin.R
import com.example.kotlin.mvp.androidmvparchitexturekotlin.R.id
import com.example.kotlin.mvp.androidmvparchitexturekotlin.databinding.ActivityHomeBinding
import com.example.kotlin.mvp.androidmvparchitexturekotlin.ui.base.BaseActivity
import com.example.kotlin.mvp.androidmvparchitexturekotlin.ui.home.HomeContract.ContractView
import com.example.kotlin.mvp.androidmvparchitexturekotlin.ui.home.gallery.GalleryFragment
import com.example.kotlin.mvp.androidmvparchitexturekotlin.ui.home.item.HomeFragment
import com.example.kotlin.mvp.androidmvparchitexturekotlin.ui.home.slideshow.SlideshowFragment
import com.example.kotlin.mvp.androidmvparchitexturekotlin.utils.SessionManager
import javax.inject.Inject
import com.example.kotlin.mvp.androidmvparchitexturekotlin.ui.login.LoginActivity


class home : BaseActivity(), ContractView, BottomNavigationView.OnNavigationItemSelectedListener, NavigationView.OnNavigationItemSelectedListener  {

  private lateinit var appBarConfiguration: AppBarConfiguration
  private lateinit var binding: ActivityHomeBinding

  private var sessionManager: SessionManager? = null
  override fun setUp() {

  }
  private lateinit var  toggle : ActionBarDrawerToggle

  @Inject
  lateinit var mPresenter: HomePresenter

  init {

    NewsApp.mNewsComponent?.inject(this)

    mPresenter.attachView(this)

  }
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = ActivityHomeBinding.inflate(layoutInflater)
    setContentView(binding.root)

    setSupportActionBar(binding.appBarHome.toolbar)


    binding = ActivityHomeBinding.inflate(layoutInflater)
    setContentView(binding.root)

    setSupportActionBar(binding.appBarHome.toolbar)

    toggle = ActionBarDrawerToggle(
      this, binding.drawerLayout, binding.appBarHome.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
    )
    binding.drawerLayout.addDrawerListener(toggle)
    toggle.syncState()
    binding.navView.setNavigationItemSelectedListener(this)
    binding.btnMain.setOnNavigationItemSelectedListener(this)

    loadFragment(HomeFragment())
    binding.navView.run {
     val headerView = LayoutInflater.from(this@home).inflate(R.layout.nav_header_home, this, false);
      addHeaderView(headerView);

      val iv = headerView.findViewById<ImageView>(R.id.imageView)
      val nama = headerView.findViewById<TextView>(R.id.nama)
      val email = headerView.findViewById<TextView>(R.id.email)
      sessionManager = SessionManager(this@home)
      sessionManager?.init()
      val user = sessionManager?.getUserDetails()
      if (user?.isNotEmpty() ==true) {
        nama.text = user?.get("nama").toString()
        email.text = user?.get("user").toString()
        Glide.with(this@home)
          .load(user?.get("foto"))
          .error(R.drawable.icon)
          .into(iv)
      }
    }
  }

  override fun onCreateOptionsMenu(menu: Menu): Boolean {
    // Inflate the menu; this adds items to the action bar if it is present.
    menuInflater.inflate(R.menu.home, menu)
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    return when (item.itemId) {
      R.id.action_settings ->   {
        sessionManager?.clearData()
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
        return true
      }
      // Action goes here
      else -> super.onOptionsItemSelected(item)
    }
  }


  override fun onSupportNavigateUp(): Boolean {
    val navController = findNavController(id.nav_host_fragment_content_home)
    return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
  }
  private fun loadFragment(fragment: Fragment?): Boolean {
    if (fragment != null) {
      supportFragmentManager.beginTransaction()
        .replace(id.nav_host_fragment_content_home, fragment)
        .commit()
      return true
    }
    return false
  }

  override fun onNavigationItemSelected(item: MenuItem): Boolean {
    if (item.itemId != id.account_menu) {
      for (fragment in supportFragmentManager.fragments) {
        supportFragmentManager.beginTransaction().remove(fragment!!).commit()
      }
    }

    var fragment: Fragment? = null
    when (item.itemId) {
      id.home_menu -> fragment = HomeFragment()
      id.search_menu -> fragment = SlideshowFragment()
      id.favorite_menu -> fragment = GalleryFragment()
      id.logout ->  {
        sessionManager?.clearData()
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
        return false
      }
      id.account_menu -> {
        binding.drawerLayout.openDrawer(GravityCompat.START)
        return false
      }
    }

    binding.drawerLayout.closeDrawer(GravityCompat.START);
    return loadFragment(fragment)
  }
}