package com.example.kotlin.mvp.androidmvparchitexturekotlin.data.remote.model

import com.example.kotlin.mvp.androidmvparchitexturekotlin.data.local.entities.ArticleEntity
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Ali DOUIRI on 27/04/2018.
 * my.alidouiri@gmail.com
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