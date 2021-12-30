package com.example.kotlin.mvp.androidmvparchitexturekotlin.data.remote.model

import com.example.kotlin.mvp.androidmvparchitexturekotlin.data.local.entities.ArticleEntity
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Ali DOUIRI on 27/04/2018.
 * my.alidouiri@gmail.com
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