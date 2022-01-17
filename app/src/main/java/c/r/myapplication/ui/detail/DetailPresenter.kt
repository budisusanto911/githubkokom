package c.r.myapplication.ui.detail

import android.util.Log
import c.r.myapplication.data.local.LocalDataSource
import c.r.myapplication.data.remote.RemoteDataSource
import c.r.myapplication.ui.base.BasePresenter
import c.r.myapplication.ui.detail.DetailContract.ContractPresenter
import c.r.myapplication.ui.detail.DetailContract.ContractView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Ali DOUIRI on 27/04/2018.
 * my.alidouiri@gmail.com
 */

class DetailPresenter(
  val mLocalDataSource: LocalDataSource,
  val mRemoteDataSource: RemoteDataSource,
) : BasePresenter<ContractView>(),
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
          val total = response.body()?.total
          response.body()?.data?.let {
            getView()?.setTotal(it[0], total ?: 0.0)
            getView()?.onArtilesReady(it)
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


}