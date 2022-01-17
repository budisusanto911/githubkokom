package c.r.myapplication.ui.detail

import c.r.myapplication.data.remote.model.ItemHome
import c.r.myapplication.ui.base.IBaseView

/**
 * Created by Ali DOUIRI on 27/04/2018.
 * my.alidouiri@gmail.com
 */

interface DetailContract {

    interface ContractPresenter {

        fun getArticlesFromApi(user: String)

    }

    interface ContractView : IBaseView {
        fun setLink(link: String)
        fun setTotal(item: ItemHome, totalItem: Double)
        fun onArtilesReady(items: List<ItemHome>)
    }
}