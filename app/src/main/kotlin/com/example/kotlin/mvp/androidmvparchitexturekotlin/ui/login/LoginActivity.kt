package com.example.kotlin.mvp.androidmvparchitexturekotlin.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.kotlin.mvp.androidmvparchitexturekotlin.NewsApp
import com.example.kotlin.mvp.androidmvparchitexturekotlin.R
import com.example.kotlin.mvp.androidmvparchitexturekotlin.data.local.entities.ArticleEntity
import com.example.kotlin.mvp.androidmvparchitexturekotlin.data.remote.model.Login
import com.example.kotlin.mvp.androidmvparchitexturekotlin.databinding.ActivityLoginBinding
import com.example.kotlin.mvp.androidmvparchitexturekotlin.ui.base.BaseActivity
import com.example.kotlin.mvp.androidmvparchitexturekotlin.ui.home.home
import com.example.kotlin.mvp.androidmvparchitexturekotlin.ui.login.LoginContract.ContractView
import com.example.kotlin.mvp.androidmvparchitexturekotlin.utils.SessionManager
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_login.view.password
import kotlinx.android.synthetic.main.activity_login.view.user
import javax.inject.Inject

/**
 * Created by Ali DOUIRI on 27/04/2018.
 * my.alidouiri@gmail.com
 */

class LoginActivity : BaseActivity(), ContractView {

    private final val TAG = LoginActivity::class.java.simpleName
    private lateinit var binding : ActivityLoginBinding
    @Inject
    lateinit var mPresenter: LoginPresenter

    private lateinit var sessionManager: SessionManager
    init {

        NewsApp.mNewsComponent?.inject(this)

        mPresenter.attachView(this)

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView( binding.root)
        sessionManager = SessionManager(this)
        sessionManager.init()
        binding.run {
            btnLogin.setOnClickListener {
                if (user.text.toString().isNotEmpty() && password.text.toString().isNotEmpty())
                mPresenter.getArticlesFromApi(user.text.toString(), password.text.toString())
                else Toast.makeText(this@LoginActivity, "Mohon User dan pasword diisi", Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun setUp() {


    }

    override fun toHome(id: Login) {
        if (!id.status) {
            Toast.makeText(this, "User dan password salah", Toast.LENGTH_SHORT).show()
            return
        }

        sessionManager.createLoginSession(id.itemLogin?.id, binding.user.text.toString(), binding.password.text.toString(), id.itemLogin?.name, id.itemLogin?.avatar)

        val intent = Intent(this, home::class.java)
        startActivity(intent)
         finish()
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