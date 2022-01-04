package com.example.kotlin.mvp.androidmvparchitexturekotlin.ui.home.slideshow

import android.content.Context
import android.os.AsyncTask
import android.util.Log
import com.example.kotlin.mvp.androidmvparchitexturekotlin.data.local.LocalDataSource
import com.example.kotlin.mvp.androidmvparchitexturekotlin.data.local.entities.ArticleEntity
import com.example.kotlin.mvp.androidmvparchitexturekotlin.data.remote.RemoteDataSource
import com.example.kotlin.mvp.androidmvparchitexturekotlin.ui.base.BasePresenter
import com.example.kotlin.mvp.androidmvparchitexturekotlin.ui.home.slideshow.SearchContract.ContractPresenter
import com.example.kotlin.mvp.androidmvparchitexturekotlin.ui.home.slideshow.SearchContract.ContractView
import com.example.kotlin.mvp.androidmvparchitexturekotlin.utils.NetworkUtil
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Ali DOUIRI on 27/04/2018.
 * my.alidouiri@gmail.com
 */

class SearchPresenter(val mLocalDataSource: LocalDataSource, val mRemoteDataSource: RemoteDataSource) : BasePresenter<ContractView>(),
  ContractPresenter {

    private final val TAG = SearchPresenter::class.java.simpleName



    override fun getKategori() {
        getView()?.showLoading()
        mDisposable = mRemoteDataSource.getKategori()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->

               //   Log.i(TAG, "getArticlesFromApi: " + Gson().toJson(response))
                    if (!isViewAttached()) return@subscribe

                    getView()?.hideLoading()

                    if (response.isSuccessful) {

                      response.body()?.let {
                        response.body()?.itemLogin?.let { getView()?.setKategori(it) }
                      }
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
  override fun getSubKategori(id: String) {
    getView()?.showLoading()
    mDisposable = mRemoteDataSource.getSubKategori(id)
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe({ response ->

        if (!isViewAttached()) return@subscribe

        getView()?.hideLoading()

        if (response.isSuccessful) {

          response.body()?.let {
            response.body()?.itemLogin?.let { getView()?.setSubKategori(it) }
          }
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
  override fun getPos(id: String) {
    getView()?.showLoading()
    mDisposable = mRemoteDataSource.getPos(id)
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe({ response ->

        //   Log.i(TAG, "getArticlesFromApi: " + Gson().toJson(response))
        if (!isViewAttached()) return@subscribe

        getView()?.hideLoading()

        if (response.isSuccessful) {

          response.body()?.let {
            response.body()?.itemLogin?.let { getView()?.setPos(it) }
          }
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
  override fun getSubPos(id: String) {
    getView()?.showLoading()
    mDisposable = mRemoteDataSource.getSubPos(id)
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe({ response ->

        //   Log.i(TAG, "getArticlesFromApi: " + Gson().toJson(response))
        if (!isViewAttached()) return@subscribe

        getView()?.hideLoading()

        if (response.isSuccessful) {

          response.body()?.let {
            response.body()?.itemLogin?.let { getView()?.setSupPos(it) }
          }
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
  override fun getUraian(p: String,i: String,c: String,k: String) {
    getView()?.showLoading()
    mDisposable = mRemoteDataSource.getUraian(p,i,c,k)
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe({ response ->

        //   Log.i(TAG, "getArticlesFromApi: " + Gson().toJson(response))
        if (!isViewAttached()) return@subscribe

        getView()?.hideLoading()

        if (response.isSuccessful) {

          response.body()?.let {
            response.body()?.itemLogin?.let {

            }
          }
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
    override fun saveArticles(items: List<ArticleEntity>) {

        object : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg voids: Void): Void? {
                mLocalDataSource.getArticleDao().deleteArticles()
                mLocalDataSource.getArticleDao().saveArticles(items)
                return null
            }
        }.execute()

    }
}