package c.r.myapplication.ui.home.slideshow

import android.util.Log
import c.r.myapplication.data.local.LocalDataSource
import c.r.myapplication.data.remote.RemoteDataSource
import c.r.myapplication.ui.base.BasePresenter
import c.r.myapplication.ui.home.slideshow.SearchContract.ContractPresenter
import c.r.myapplication.ui.home.slideshow.SearchContract.ContractView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Handi Komara.
 * handikomara22@gmail.com
 */

class SearchPresenter(val mLocalDataSource: LocalDataSource, val mRemoteDataSource: RemoteDataSource) : BasePresenter<ContractView>(),
  ContractPresenter {

    private final val TAG = SearchPresenter::class.java.simpleName

  override fun getArticlesFromApi(date1: String, date2: String) {
    getView()?.showLoading()
    mDisposable = mRemoteDataSource.halamancari(date1, date2)
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe({ response ->
        if (!isViewAttached()) return@subscribe

        getView()?.hideLoading()

        if (response.isSuccessful) {
          Log.i(TAG, "getArticlesFromApi: " +  response.body()?.data)
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