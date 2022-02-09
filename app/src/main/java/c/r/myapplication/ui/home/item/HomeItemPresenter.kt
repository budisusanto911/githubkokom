package c.r.myapplication.ui.home.item

import android.util.Log
import c.r.myapplication.data.local.LocalDataSource
import c.r.myapplication.data.remote.RemoteDataSource
import c.r.myapplication.ui.base.BasePresenter
import c.r.myapplication.ui.home.item.HomeItemContract.ContractPresenter
import c.r.myapplication.ui.home.item.HomeItemContract.ContractView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Handi Komara.
 * handikomara22@gmail.com
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