package c.r.myapplication.ui.add

import c.r.myapplication.data.local.entities.ArticleEntity
import c.r.myapplication.data.remote.model.ItemSpinner
import c.r.myapplication.ui.base.IBaseView
import io.reactivex.Observable
import java.io.File
import java.util.ArrayList

/**
 * Created by Ali DOUIRI on 27/04/2018.
 * my.alidouiri@gmail.com
 */

interface AddContract {

    interface ContractPresenter {

        fun saveArticles(items: List<ArticleEntity>)
        fun uploadImage(idUser : Int)
        fun setSave(no: String, foto: String, tanggal: String)
    }

    interface ContractView : IBaseView {
        fun setIdInsert(idInsert: Int)
        fun setLink(link: String, name: String)
        fun getImageCompressor(): Observable<File>
        fun onArtilesReady(items: List<ArticleEntity>)

    }
}