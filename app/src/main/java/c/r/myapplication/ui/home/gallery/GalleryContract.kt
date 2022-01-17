package c.r.myapplication.ui.home.gallery

import c.r.myapplication.data.local.entities.ArticleEntity
import c.r.myapplication.data.remote.model.ItemHome
import c.r.myapplication.data.remote.model.ItemSpinner
import c.r.myapplication.ui.base.IBaseView
import java.util.ArrayList

/**
 * Created by Ali DOUIRI on 27/04/2018.
 * my.alidouiri@gmail.com
 */

interface GalleryContract {

    interface ContractPresenter {
        fun getKategori()
        fun getSubKategori(id: String)
        fun getPos(id: String)
        fun getSubPos(id: String)
        fun getUraian(p: String,i: String,c: String,k: String)
        fun saveArticles(items: List<ArticleEntity>)

    }

    interface ContractView : IBaseView {
        fun setSupPos(list: ArrayList<ItemSpinner>)
        fun setPos(list: ArrayList<ItemSpinner>)
        fun setSubKategori(list: ArrayList<ItemSpinner>)
        fun setKategori(list: ArrayList<ItemSpinner>)
        fun onArtilesReady(items: List<ItemHome>)
    }
}