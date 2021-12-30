package com.example.kotlin.mvp.androidmvparchitexturekotlin.ui.home.item

import android.content.Context
import android.os.AsyncTask
import android.util.Log
import com.example.kotlin.mvp.androidmvparchitexturekotlin.data.local.LocalDataSource
import com.example.kotlin.mvp.androidmvparchitexturekotlin.data.local.entities.ArticleEntity
import com.example.kotlin.mvp.androidmvparchitexturekotlin.data.remote.RemoteDataSource
import com.example.kotlin.mvp.androidmvparchitexturekotlin.ui.base.BasePresenter
import com.example.kotlin.mvp.androidmvparchitexturekotlin.ui.home.item.HomeItemContract.ContractPresenter
import com.example.kotlin.mvp.androidmvparchitexturekotlin.ui.home.item.HomeItemContract.ContractView
import com.example.kotlin.mvp.androidmvparchitexturekotlin.utils.NetworkUtil
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Ali DOUIRI on 27/04/2018.
 * my.alidouiri@gmail.com
 */

class HomeItemPresenter(val mLocalDataSource: LocalDataSource, val mRemoteDataSource: RemoteDataSource) : BasePresenter<ContractView>(),
  ContractPresenter {

    private final val TAG = HomeItemPresenter::class.java.simpleName

    override fun getArticlesFromApi() {
        getView()?.showLoading()
        mDisposable = mRemoteDataSource.getHome()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                 if (!isViewAttached()) return@subscribe

                    getView()?.hideLoading()

                  if (response.isSuccessful) {
                      response.body()?.data?.let { getView()?.onArtilesReady(it) }
                    }

                },
                        { error ->
                            getView()?.hideLoading();Log.e(TAG, "{$error.message}")
                        },
                        {
                            getView()?.hideLoading()
                            Log.d(TAG, "completed")
                        })

    }

}