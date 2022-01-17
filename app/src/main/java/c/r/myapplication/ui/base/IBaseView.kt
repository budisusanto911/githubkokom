package c.r.myapplication.ui.base

import androidx.annotation.StringRes

/**
 * Created by Ali DOUIRI on 27/04/2018.
 * my.alidouiri@gmail.com
 */

interface IBaseView {

    fun showLoading()

    fun hideLoading()

    fun showError(errorMessage: String)

    fun showError(@StringRes errorId: Int)
}