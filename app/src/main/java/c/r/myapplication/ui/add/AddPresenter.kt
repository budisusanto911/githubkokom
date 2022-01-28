package c.r.myapplication.ui.add

import android.os.AsyncTask
import android.util.Log
import c.r.myapplication.data.local.LocalDataSource
import c.r.myapplication.data.local.entities.ArticleEntity
import c.r.myapplication.data.remote.RemoteDataSource
import c.r.myapplication.data.remote.model.UploadFotoRequest
import c.r.myapplication.data.remote.model.UploadResponse
import c.r.myapplication.ui.add.AddContract.ContractPresenter
import c.r.myapplication.ui.add.AddContract.ContractView
import c.r.myapplication.ui.base.BasePresenter
import com.google.gson.Gson
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AddPresenter(val mLocalDataSource: LocalDataSource, val mRemoteDataSource: RemoteDataSource) :
  BasePresenter<ContractView>(),
  ContractPresenter {

  private final val TAG = AddPresenter::class.java.simpleName



  override fun setSave(no: String, foto: String, tanggal: String) {
    getView()?.showLoading()
    mDisposable = mRemoteDataSource.submit(no, foto, tanggal)
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe({ response ->

        //   Log.i(TAG, "getArticlesFromApi: " + Gson().toJson(response))
        if (!isViewAttached()) return@subscribe

        getView()?.hideLoading()

        if (response.isSuccessful) {

          response.body()?.let {
            getView()?.setIdInsert(it.insert?:0)
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

  override fun uploadImage(idUser : Int) {
    val observable: Observable<UploadResponse>
    val request = UploadFotoRequest(
      idUser
    )
    observable = getView()?.getImageCompressor()
      ?.flatMap { compressedFile ->
        request.file = compressedFile
        mRemoteDataSource.uploadFoto(request)
      } as Observable<UploadResponse>
    mDisposable = observable
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe({response ->
           Log.i(TAG, "getArticlesFromApi: " + Gson().toJson(response))
        if (!isViewAttached()) return@subscribe

        getView()?.hideLoading()
        getView()?.setLink(response.url?:"", response.files?:"")

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
      /*  mLocalDataSource.getArticleDao().deleteArticles()
        mLocalDataSource.getArticleDao().saveArticles(items)*/
        return null
      }
    }.execute()

  }
}