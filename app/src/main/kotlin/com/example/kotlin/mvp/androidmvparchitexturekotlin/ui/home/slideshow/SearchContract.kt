package com.example.kotlin.mvp.androidmvparchitexturekotlin.ui.home.slideshow

import android.content.Context
import android.widget.Spinner
import com.example.kotlin.mvp.androidmvparchitexturekotlin.data.local.entities.ArticleEntity
import com.example.kotlin.mvp.androidmvparchitexturekotlin.data.remote.model.ItemSpinner
import com.example.kotlin.mvp.androidmvparchitexturekotlin.data.remote.model.Login
import com.example.kotlin.mvp.androidmvparchitexturekotlin.ui.base.IBaseView
import java.util.ArrayList

/**
 * Created by Ali DOUIRI on 27/04/2018.
 * my.alidouiri@gmail.com
 */

interface SearchContract {

    interface ContractPresenter {
        fun getKategori()
        fun getSubKategori(id: String)
        fun getPos(id: String)
        fun getSubPos(id: String)
        fun getUraian(p: String,i: String,c: String,k: String)
        fun saveArticles(items: List<ArticleEntity>)

    }

    interface ContractView : IBaseView {
        fun setSupPos(list: ArrayList<ItemSpinner>)
        fun setPos(list: ArrayList<ItemSpinner>)
        fun setSubKategori(list: ArrayList<ItemSpinner>)
        fun setKategori(list: ArrayList<ItemSpinner>)
        fun onArtilesReady(items: List<ArticleEntity>)

    }
}