package c.r.myapplication.utils

import android.content.Context
import android.net.ConnectivityManager

/**
 * Created by Handi Komara.
 * handikomara22@gmail.com
 */

object NetworkUtil {

    fun isNetworkConnected(context: Context): Boolean {

        val mConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val mActiveNetwork = mConnectivityManager.activeNetworkInfo
        return mActiveNetwork != null && mActiveNetwork.isConnectedOrConnecting

    }

}