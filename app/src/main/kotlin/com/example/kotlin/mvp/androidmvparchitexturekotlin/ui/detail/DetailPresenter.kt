package com.example.kotlin.mvp.androidmvparchitexturekotlin.ui.detail

import android.content.Context
import android.os.AsyncTask
import android.util.Log
import com.example.kotlin.mvp.androidmvparchitexturekotlin.data.local.LocalDataSource
import com.example.kotlin.mvp.androidmvparchitexturekotlin.data.remote.RemoteDataSource
import com.example.kotlin.mvp.androidmvparchitexturekotlin.ui.base.BasePresenter
import com.example.kotlin.mvp.androidmvparchitexturekotlin.ui.detail.DetailContract.ContractPresenter
import com.example.kotlin.mvp.androidmvparchitexturekotlin.ui.detail.DetailContract.ContractView
import com.example.kotlin.mvp.androidmvparchitexturekotlin.utils.NetworkUtil
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Ali DOUIRI on 27/04/2018.
 * my.alidouiri@gmail.com
 */

class DetailPresenter(val mLocalDataSource: LocalDataSource, val mRemoteDataSource: RemoteDataSource) : BasePresenter<ContractView>(),
  ContractPresenter {

    private final val TAG = DetailPresenter::class.java.simpleName



    override fun getArticlesFromApi(user: String) {
        getView()?.showLoading()
        mDisposable = mRemoteDataSource.getHomeF(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->

               //   Log.i(TAG, "getArticlesFromApi: " + Gson().toJson(response))
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