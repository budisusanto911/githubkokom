package com.example.kotlin.mvp.androidmvparchitexturekotlin.ui.login

import android.content.Context
import com.example.kotlin.mvp.androidmvparchitexturekotlin.data.local.entities.ArticleEntity
import com.example.kotlin.mvp.androidmvparchitexturekotlin.data.remote.model.Login
import com.example.kotlin.mvp.androidmvparchitexturekotlin.ui.base.IBaseView

/**
 * Created by Ali DOUIRI on 27/04/2018.
 * my.alidouiri@gmail.com
 */

interface LoginContract {

    interface ContractPresenter {

        fun getArticles(context: Context)

        fun getArticlesFromApi(user: String, password: String)

        fun getArticleFromDb()

        fun saveArticles(items: List<ArticleEntity>)

    }

    interface ContractView : IBaseView {

        fun toHome(id: Login)
        fun onArtilesReady(items: List<ArticleEntity>)

    }
}