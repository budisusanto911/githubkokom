package c.r.myapplication.ui.home

import android.content.Context
import android.os.AsyncTask
import android.util.Log
import c.r.myapplication.data.local.LocalDataSource
import c.r.myapplication.data.local.entities.ArticleEntity
import c.r.myapplication.data.remote.RemoteDataSource
import c.r.myapplication.ui.base.BasePresenter
import c.r.myapplication.ui.home.HomeContract.ContractPresenter
import c.r.myapplication.ui.home.HomeContract.ContractView
import c.r.myapplication.utils.NetworkUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Handi Komara.
 * handikomara22@gmail.com
 */

class HomePresenter(val mLocalDataSource: LocalDataSource, val mRemoteDataSource: RemoteDataSource) : BasePresenter<ContractView>(),
  ContractPresenter {

    private final val TAG = HomePresenter::class.java.simpleName

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

               //   Log.i(TAG, "getArticlesFromApi: " + Gson().toJson(response))
                    if (!isViewAttached()) return@subscribe

                    getView()?.hideLoading()

                    if (response.isSuccessful) {

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

  override fun getArticleFromDb() {
    TODO("Not yet implemented")
  }

  /* override fun getArticleFromDb() {

       getView()?.showLoading()

       mLocalDataSource.getArticleDao().getArticlesFromDb()
               .subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe({ response ->

                   if (!isViewAttached()) return@subscribe

                   getView()?.hideLoading()


               },
                       { error ->
                           getView()?.hideLoading();Log.e(TAG, "{$error.message}")
                       },
                       {
                           getView()?.hideLoading()
                           Log.d(TAG, "completed")
                       })

   }*/

    override fun saveArticles(items: List<ArticleEntity>) {

        object : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg voids: Void): Void? {
               /* mLocalDataSource.getArticleDao().deleteArticles()
                mLocalDataSource.getArticleDao().saveArticles(items)*/
                return null
            }
        }.execute()

    }
}