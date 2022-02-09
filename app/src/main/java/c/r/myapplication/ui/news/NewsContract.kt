package c.r.myapplication.ui.news

import android.content.Context
import c.r.myapplication.data.local.entities.ArticleEntity
import c.r.myapplication.ui.base.IBaseView

/**
 * Created by Handi Komara.
 * handikomara22@gmail.com
 */

interface NewsContract {

    interface ContractPresenter {

        fun getArticles(context: Context)

        fun getArticlesFromApi()

        fun getArticleFromDb()

        fun saveArticles(items: List<ArticleEntity>)

    }

    interface ContractView : IBaseView {

        fun onArtilesReady(items: List<ArticleEntity>)

    }
}