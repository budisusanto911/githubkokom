package c.r.myapplication.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import c.r.myapplication.NewsApp
import c.r.myapplication.R
import c.r.myapplication.data.local.entities.ArticleEntity
import c.r.myapplication.ui.base.BaseActivity
import c.r.myapplication.ui.home.home
import c.r.myapplication.ui.login.LoginActivity
import c.r.myapplication.ui.splash.SplashContract.ContractView
import c.r.myapplication.utils.SessionManager
import javax.inject.Inject

/**
 * Created by Handi Komara.
 * handikomara22@gmail.com
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
        if (items.isNotEmpty()) {
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