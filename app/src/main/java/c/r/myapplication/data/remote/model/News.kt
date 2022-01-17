package c.r.myapplication.data.remote.model

import c.r.myapplication.data.local.entities.ArticleEntity
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Ali DOUIRI on 27/04/2018.
 * my.alidouiri@gmail.com
 */

data class News(

        @Expose
        @SerializedName("articles")
        var mArticles: List<ArticleEntity>? = null,

        @Expose
        @SerializedName("totalResults")
        var mTotalResults: Int = 0,

        @Expose
        @SerializedName("status")
        var mStatus: String? = null
)