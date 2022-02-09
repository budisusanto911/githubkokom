package c.r.myapplication.ui.login

import android.content.Context
import c.r.myapplication.data.local.entities.ArticleEntity
import c.r.myapplication.data.remote.model.Login
import c.r.myapplication.ui.base.IBaseView

/**
 * Created by Handi Komara.
 * handikomara22@gmail.com
 */

interface LoginContract {

    interface ContractPresenter {

        fun getArticles(context: Context)

        fun getArticlesFromApi(user: String, password: String)

        fun getArticleFromDb()

        fun saveArticles(items: List<ArticleEntity>)

    }

    interface ContractView : IBaseView {

        fun toHome(id: Login)
        fun onArtilesReady(items: List<ArticleEntity>)

    }
}