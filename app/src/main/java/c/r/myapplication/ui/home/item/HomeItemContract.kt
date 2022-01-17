package c.r.myapplication.ui.home.item

import c.r.myapplication.data.remote.model.ItemHome
import c.r.myapplication.ui.base.IBaseView

/**
 * Created by Ali DOUIRI on 27/04/2018.
 * my.alidouiri@gmail.com
 */

interface HomeItemContract {

    interface ContractPresenter {

        fun getArticlesFromApi()

    }

    interface ContractView : IBaseView {

        fun onArtilesReady(items: List<ItemHome>)
        fun onItemClick(item : ItemHome)
        fun setUp()
    }
}