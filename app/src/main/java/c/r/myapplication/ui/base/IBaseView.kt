package c.r.myapplication.ui.base

import androidx.annotation.StringRes

/**
 * Created by Handi Komara.
 * handikomara22@gmail.com
 */

interface IBaseView {

    fun showLoading()

    fun hideLoading()

    fun showError(errorMessage: String)

    fun showError(@StringRes errorId: Int)
}