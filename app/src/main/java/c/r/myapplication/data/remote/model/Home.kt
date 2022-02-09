package c.r.myapplication.data.remote.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Handi Komara.
 * handikomara22@gmail.com
 */

data class Home(

        @Expose
        @SerializedName("data")
        var data: ArrayList<ItemHome>? = arrayListOf(),

        @Expose
        @SerializedName("success")
        var success: Int = 0,

        @Expose
        @SerializedName("total")
        var total: Double = 0.0,

        @Expose
        @SerializedName("status")
        var status: Boolean = false,
)