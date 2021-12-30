package com.example.kotlin.mvp.androidmvparchitexturekotlin.ui.detail

import android.content.Context
import com.example.kotlin.mvp.androidmvparchitexturekotlin.data.local.entities.ArticleEntity
import com.example.kotlin.mvp.androidmvparchitexturekotlin.data.remote.model.ItemHome
import com.example.kotlin.mvp.androidmvparchitexturekotlin.data.remote.model.Login
import com.example.kotlin.mvp.androidmvparchitexturekotlin.ui.base.IBaseView

/**
 * Created by Ali DOUIRI on 27/04/2018.
 * my.alidouiri@gmail.com
 */

interface DetailContract {

    interface ContractPresenter {

        fun getArticlesFromApi(user: String)

    }

    interface ContractView : IBaseView {
        fun onArtilesReady(items: List<ItemHome>)
    }
}