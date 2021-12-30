package com.example.kotlin.mvp.androidmvparchitexturekotlin.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.example.kotlin.mvp.androidmvparchitexturekotlin.NewsApp
import com.example.kotlin.mvp.androidmvparchitexturekotlin.R
import com.example.kotlin.mvp.androidmvparchitexturekotlin.data.local.entities.ArticleEntity
import com.example.kotlin.mvp.androidmvparchitexturekotlin.ui.base.BaseActivity
import com.example.kotlin.mvp.androidmvparchitexturekotlin.ui.home.home
import com.example.kotlin.mvp.androidmvparchitexturekotlin.ui.login.LoginActivity
import com.example.kotlin.mvp.androidmvparchitexturekotlin.ui.splash.SplashContract.ContractView
import com.example.kotlin.mvp.androidmvparchitexturekotlin.utils.SessionManager
import javax.inject.Inject

/**
 * Created by Ali DOUIRI on 27/04/2018.
 * my.alidouiri@gmail.com
 */

class SplashActivity : BaseActivity(), ContractView {

    private final val TAG = SplashActivity::class.java.simpleName

    private var sessionManager: SessionManager? = null
    @Inject
    lateinit var mPresenter: SplashPresenter

    init {

        NewsApp.mNewsComponent?.inject(this)

        mPresenter.attachView(this)



    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sessionManager = SessionManager(this)
        sessionManager?.init()
        setContentView(R.layout.activity_splash_screen)

        setUp()

    }

    override fun setUp() {
        Handler().postDelayed(Runnable {
            intent = if (sessionManager?.isLoggedIn() == true)
                Intent(this, home::class.java)
            else   Intent(this, LoginActivity::class.java)
                startActivity(intent)
            finish()
        }, 2000)
    }

    override fun onArtilesReady(items: List<ArticleEntity>) {

        if (items != null && !items.isEmpty()) {

            hideErrorContainer()

        } else {

            showErrorContainer()
        }

    }



    private fun showErrorContainer() {


    }

    private fun hideErrorContainer() {



    }


    override fun onDestroy() {

        mPresenter?.detachView()
        super.onDestroy()
    }

}