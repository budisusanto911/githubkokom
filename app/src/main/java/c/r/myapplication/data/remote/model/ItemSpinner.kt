package c.r.myapplication.data.remote.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Ali DOUIRI on 27/04/2018.
 * my.alidouiri@gmail.com
 */

data class ItemSpinner(

        @Expose
        @SerializedName("item")
        var item: String = ""
)