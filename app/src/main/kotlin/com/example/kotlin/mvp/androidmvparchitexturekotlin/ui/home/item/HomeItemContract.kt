package com.example.kotlin.mvp.androidmvparchitexturekotlin.ui.home.item

import android.content.Context
import com.example.kotlin.mvp.androidmvparchitexturekotlin.data.local.entities.ArticleEntity
import com.example.kotlin.mvp.androidmvparchitexturekotlin.data.remote.model.ItemHome
import com.example.kotlin.mvp.androidmvparchitexturekotlin.ui.base.IBaseView

/**
 * Created by Ali DOUIRI on 27/04/2018.
 * my.alidouiri@gmail.com
 */

interface HomeItemContract {

    interface ContractPresenter {

        fun getArticlesFromApi()

    }

    interface ContractView : IBaseView {

        fun onArtilesReady(items: List<ItemHome>)
        fun onItemClick(item : ItemHome)
        fun setUp()
    }
}