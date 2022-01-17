package c.r.myapplication.ui.home

import android.content.Context
import c.r.myapplication.data.local.entities.ArticleEntity
import c.r.myapplication.ui.base.IBaseView

/**
 * Created by Ali DOUIRI on 27/04/2018.
 * my.alidouiri@gmail.com
 */

interface HomeContract {

    interface ContractPresenter {

        fun getArticles(context: Context)

        fun getArticlesFromApi(user: String, password: String)

        fun getArticleFromDb()

        fun saveArticles(items: List<ArticleEntity>)

    }

    interface ContractView : IBaseView {


    }
}