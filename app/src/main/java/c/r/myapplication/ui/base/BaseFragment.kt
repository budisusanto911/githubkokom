package c.r.myapplication.ui.base

import android.content.Context
import androidx.fragment.app.Fragment

/**
 * Created by Ali DOUIRI on 27/04/2018.
 * my.alidouiri@gmail.com
 */

abstract class BaseFragment : Fragment(), IBaseView {

    private var mActivity: BaseActivity? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is BaseActivity) {
            mActivity = context
        }
    }
    protected abstract fun initViews()

    override fun showLoading() {

        mActivity?.showLoading()
    }

    override fun hideLoading() {

        mActivity?.hideLoading()

    }

    override fun showError(errorMessage: String) {

        mActivity?.showError(errorMessage)

    }

    override fun showError(errorId: Int) {

        mActivity?.showError(errorId)
    }

}