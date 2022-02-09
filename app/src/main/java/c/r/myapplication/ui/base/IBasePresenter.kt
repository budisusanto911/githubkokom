package c.r.myapplication.ui.base

/**
 * Created by Handi Komara.
 * handikomara22@gmail.com
 */

interface IBasePresenter<V : IBaseView> {

    fun attachView(view: V)

    fun detachView()

    fun isViewAttached(): Boolean
}