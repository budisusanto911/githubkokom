package c.r.myapplication.ui.detail

import c.r.myapplication.data.remote.model.ItemHome
import c.r.myapplication.ui.base.IBaseView


interface DetailContract {

    interface ContractPresenter {

        fun getArticlesFromApi(user: String)
        fun save(user: String, status: String)
    }

    interface ContractView : IBaseView {
        fun setLink(link: String)
        fun setVisibility(status: Boolean)
        fun setTotal(item: ItemHome, totalItem: Double)
        fun onArtilesReady(items: List<ItemHome>)
    }
}