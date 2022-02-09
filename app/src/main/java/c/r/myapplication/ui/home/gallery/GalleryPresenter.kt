package c.r.myapplication.ui.home.gallery

import android.os.AsyncTask
import android.util.Log
import c.r.myapplication.data.local.LocalDataSource
import c.r.myapplication.data.local.entities.ArticleEntity
import c.r.myapplication.data.remote.RemoteDataSource
import c.r.myapplication.ui.base.BasePresenter
import c.r.myapplication.ui.home.gallery.GalleryContract.ContractPresenter
import c.r.myapplication.ui.home.gallery.GalleryContract.ContractView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Handi Komara.
 * handikomara22@gmail.com
 */

class GalleryPresenter(val mLocalDataSource: LocalDataSource, val mRemoteDataSource: RemoteDataSource) : BasePresenter<ContractView>(),
  ContractPresenter {

    private final val TAG = GalleryPresenter::class.java.simpleName



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
    mDisposable = mRemoteDataSource.getCari(p,i,c,k)
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe({ response ->

        //   Log.i(TAG, "getArticlesFromApi: " + Gson().toJson(response))
        if (!isViewAttached()) return@subscribe

        getView()?.hideLoading()

        if (response.isSuccessful) {

          response.body()?.let {
            response.body()?.data?.let {
              getView()?.onArtilesReady(it)
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
                /*mLocalDataSource.getArticleDao().deleteArticles()
                mLocalDataSource.getArticleDao().saveArticles(items)*/
                return null
            }
        }.execute()

    }
}