package c.r.myapplication.ui.adddetail

import c.r.myapplication.data.local.entities.ArticleEntity
import c.r.myapplication.data.remote.model.ItemSpinner
import c.r.myapplication.ui.base.IBaseView
import java.util.ArrayList

/**
 * Created by Handi Komara.
 * handikomara22@gmail.com
 */

interface AdddetailContract {

    interface ContractPresenter {
        fun setSave(cost_code: String,
            cr_id_hdr: String,
            cr_tanggal: String,
            cr_dtl_nominal: String,
            cr_uraian: String,
            cr_user: String
        )
        fun getKategori()
        fun getSubKategori(id: String)
        fun getPos(id: String)
        fun getSubPos(id: String)
        fun getUraian(p: String,i: String,c: String,k: String)
    }

    interface ContractView : IBaseView {
        fun onArtilesReady(items: List<ArticleEntity>)
        fun setIdInsert(idInsert: Int)
        fun setItem(list: ItemSpinner)
        fun setSupPos(list: ArrayList<ItemSpinner>)
        fun setPos(list: ArrayList<ItemSpinner>)
        fun setSubKategori(list: ArrayList<ItemSpinner>)
        fun setKategori(list: ArrayList<ItemSpinner>)
    }
}