package com.example.kotlin.mvp.androidmvparchitexturekotlin.data.remote.model

import com.example.kotlin.mvp.androidmvparchitexturekotlin.data.local.entities.ArticleEntity
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
        @SerializedName("status")
        var status: Boolean = false
)