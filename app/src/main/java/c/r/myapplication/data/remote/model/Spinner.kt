package c.r.myapplication.data.remote.model

import c.r.myapplication.data.remote.model.ItemSpinner
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Handi Komara.
 * handikomara22@gmail.com
 */

data class Spinner(

        @Expose
        @SerializedName("data")
        var itemLogin: ArrayList<ItemSpinner>? = null,

        @Expose
        @SerializedName("success")
        var success: Int = 0,

        @Expose
        @SerializedName("status")
        var status: Boolean = false
)