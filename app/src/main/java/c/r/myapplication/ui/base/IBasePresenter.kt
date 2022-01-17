package c.r.myapplication.ui.base

/**
 * Created by Ali DOUIRI on 27/04/2018.
 * my.alidouiri@gmail.com
 */

interface IBasePresenter<V : IBaseView> {

    fun attachView(view: V)

    fun detachView()

    fun isViewAttached(): Boolean
}