package c.r.myapplication.ui.login

import android.annotation.SuppressLint
import android.content.Context
import android.os.AsyncTask
import android.util.Log
import c.r.myapplication.data.local.LocalDataSource
import c.r.myapplication.data.local.entities.ArticleEntity
import c.r.myapplication.data.remote.RemoteDataSource
import c.r.myapplication.ui.base.BasePresenter
import c.r.myapplication.ui.login.LoginContract.ContractPresenter
import c.r.myapplication.ui.login.LoginContract.ContractView
import c.r.myapplication.utils.NetworkUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Ali DOUIRI on 27/04/2018.
 * my.alidouiri@gmail.com
 */

class LoginPresenter(val mLocalDataSource: LocalDataSource, val mRemoteDataSource: RemoteDataSource) : BasePresenter<ContractView>(),
  ContractPresenter {

    override fun getArticles(context: Context) {

        if (NetworkUtil.isNetworkConnected(context))

        else
            getArticleFromDb()
    }

    override fun getArticlesFromApi(user: String, password: String) {
        getView()?.showLoading()
        mDisposable = mRemoteDataSource.login(user, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->

                    if (!isViewAttached()) return@subscribe

                    getView()?.hideLoading()

                    if (response.isSuccessful) {

                      response.body()?.let { getView()?.toHome(it) }
                    }

                },
                        { error ->
                            getView()?.hideLoading();Log.e("TAG", "{$error.message}")
                        },
                        {
                            getView()?.hideLoading()
                            Log.d("TAG", "completed")
                        })

    }

    @SuppressLint("CheckResult")
    override fun getArticleFromDb() {

    }

    override fun saveArticles(items: List<ArticleEntity>) {

        object : AsyncTask<Void, Void, Void>() {
            @SuppressLint("StaticFieldLeak")
            override fun doInBackground(vararg voids: Void): Void? {

                return null
            }
        }.execute()

    }
}