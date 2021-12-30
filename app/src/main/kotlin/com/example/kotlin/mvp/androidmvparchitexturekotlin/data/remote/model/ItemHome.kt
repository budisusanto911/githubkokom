package com.example.kotlin.mvp.androidmvparchitexturekotlin.data.remote.model

import com.example.kotlin.mvp.androidmvparchitexturekotlin.data.local.entities.ArticleEntity
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Ali DOUIRI on 27/04/2018.
 * my.alidouiri@gmail.com
 */

data class ItemHome(

        @Expose
        @SerializedName("cr_id_hdr")
        var cr_id_hdr: String = "",

        @Expose
        @SerializedName("cr_no_hdr")
        var cr_no_hdr: String = "",

        @Expose
        @SerializedName("cr_foto")
        var cr_foto: String? = null,

        @Expose
        @SerializedName("cr_tanggal")
        var cr_tanggal: String? = null,

        @Expose
        @SerializedName("total")
        var total: Double? = null,

        @Expose
        @SerializedName("index")
        var index: Int? = null,
        @Expose
        @SerializedName("cr_id_dtl")
        var cr_id_dtl: String? = null,
        @Expose
        @SerializedName("cr_dtl_nominal")
        var cr_dtl_nominal: Double? = null,
        @Expose
        @SerializedName("cr_uraian")
        var cr_uraian: String? = null,
)