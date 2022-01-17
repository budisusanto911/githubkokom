package c.r.myapplication.ui.home.slideshow

import c.r.myapplication.data.remote.model.ItemHome
import c.r.myapplication.ui.base.IBaseView

/**
 * Created by Ali DOUIRI on 27/04/2018.
 * my.alidouiri@gmail.com
 */

interface SearchContract {

    interface ContractPresenter {
        fun getArticlesFromApi(date1: String, date2: String)
    }

    interface ContractView : IBaseView {
        fun onArtilesReady(items: List<ItemHome>)

    }
}