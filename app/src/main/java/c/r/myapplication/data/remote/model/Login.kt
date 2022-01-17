package c.r.myapplication.data.remote.model

import c.r.myapplication.data.remote.model.ItemLogin
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Ali DOUIRI on 27/04/2018.
 * my.alidouiri@gmail.com
 */

data class Login(

        @Expose
        @SerializedName("data")
        var itemLogin: ItemLogin? = null,

        @Expose
        @SerializedName("success")
        var success: Int = 0,

        @Expose
        @SerializedName("insert")
        var insert: Int = 0,

        @Expose
        @SerializedName("status")
        var status: Boolean = false
)