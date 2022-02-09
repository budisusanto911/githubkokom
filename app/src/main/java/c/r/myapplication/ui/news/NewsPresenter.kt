package c.r.myapplication.ui.news

import android.content.Context
import android.os.AsyncTask
import android.util.Log
import c.r.myapplication.data.local.LocalDataSource
import c.r.myapplication.data.local.entities.ArticleEntity
import c.r.myapplication.data.remote.RemoteDataSource
import c.r.myapplication.ui.base.BasePresenter
import c.r.myapplication.ui.news.NewsContract.ContractView
import c.r.myapplication.utils.NetworkUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Handi Komara.
 * handikomara22@gmail.com
 */

class NewsPresenter(val mLocalDataSource: LocalDataSource, val mRemoteDataSource: RemoteDataSource) : BasePresenter<ContractView>(), NewsContract.ContractPresenter {

    private final val TAG = NewsPresenter::class.java.simpleName

    override fun getArticles(context: Context) {

        if (NetworkUtil.isNetworkConnected(context))
            getArticlesFromApi()
        else
            getArticleFromDb()
    }

    override fun getArticlesFromApi() {

        getView()?.showLoading()

        mDisposable = mRemoteDataSource.getAriclesFromApi()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->

                    if (!isViewAttached()) return@subscribe

                    getView()?.hideLoading()

                    if (response.isSuccessful) {
                        saveArticles(response.body()?.mArticles!!)
                        getView()?.onArtilesReady(response.body()?.mArticles!!)
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


    }

    override fun saveArticles(items: List<ArticleEntity>) {



    }
}