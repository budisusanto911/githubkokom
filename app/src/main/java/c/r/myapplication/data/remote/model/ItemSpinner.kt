package c.r.myapplication.data.remote.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Handi Komara.
 * handikomara22@gmail.com
 */

data class ItemSpinner(

        @Expose
        @SerializedName("item")
        var item: String = ""
)