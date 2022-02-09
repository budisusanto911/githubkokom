package c.r.myapplication.data.remote.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Handi Komara.
 * handikomara22@gmail.com
 */

data class ItemLogin(

        @Expose
        @SerializedName("id")
        var id: String = "",

        @Expose
        @SerializedName("name")
        var name: String = "",

        @Expose
        @SerializedName("email")
        var email: String? = null,

        @Expose
        @SerializedName("avatar")
        var avatar: String? = null
)