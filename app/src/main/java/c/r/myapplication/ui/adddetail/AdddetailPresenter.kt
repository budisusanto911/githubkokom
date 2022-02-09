package c.r.myapplication.ui.adddetail

import android.util.Log
import c.r.myapplication.data.local.LocalDataSource
import c.r.myapplication.data.remote.RemoteDataSource

import c.r.myapplication.ui.adddetail.AdddetailContract.ContractPresenter
import c.r.myapplication.ui.adddetail.AdddetailContract.ContractView
import c.r.myapplication.ui.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Handi Komara.
 * handikomara22@gmail.com
 */

class AdddetailPresenter(val mLocalDataSource: LocalDataSource, val mRemoteDataSource: RemoteDataSource) :
  BasePresenter<ContractView>(),
  ContractPresenter {

  private final val TAG = AdddetailPresenter::class.java.simpleName

  override fun setSave( cost_code: String,
    cr_id_hdr: String,
    cr_tanggal: String,
    cr_dtl_nominal: String,
    cr_uraian: String,
    cr_user: String
  ) {
    getView()?.showLoading()
    mDisposable = mRemoteDataSource.submitdetail(cost_code, cr_id_hdr, cr_tanggal, cr_dtl_nominal, cr_uraian, cr_user)
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe({ response ->

        //   Log.i(TAG, "getArticlesFromApi: " + Gson().toJson(response))
        if (!isViewAttached()) return@subscribe

        getView()?.hideLoading()

        if (response.isSuccessful) {

          response.body()?.let {
            getView()?.setIdInsert(it.insert)
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

  override fun getUraian(p: String, i: String, c: String, k: String) {
    // getView()?.showLoading()
    mDisposable = mRemoteDataSource.getUraian(p, i, c, k)
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe({ response ->

        //   Log.i(TAG, "getArticlesFromApi: " + Gson().toJson(response))
        if (!isViewAttached()) return@subscribe

        //getView()?.hideLoading()

        if (response.isSuccessful) {

          response.body()?.let {
            response.body()?.itemLogin?.let { getView()?.setItem(it[0]) }
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