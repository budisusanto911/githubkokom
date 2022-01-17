package c.r.myapplication.utils

import android.app.ProgressDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import c.r.myapplication.R

/**
 * Created by Ali DOUIRI on 27/04/2018.
 * my.alidouiri@gmail.com
 */

object DialogUtil {

    fun showLoadingDialog(context: Context): ProgressDialog {

        val mProgressDialog = ProgressDialog(context)
        mProgressDialog.show()

        if (mProgressDialog.window != null)
            mProgressDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        mProgressDialog.setContentView(R.layout.progress_dialog)
        mProgressDialog.isIndeterminate = true
        mProgressDialog.setCancelable(true)
        mProgressDialog.setCanceledOnTouchOutside(false)

        return mProgressDialog
    }

}