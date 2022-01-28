package c.r.myapplication.data.remote.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class ItemHome(

        @Expose
        @SerializedName("cr_id_hdr")
        var cr_id_hdr: String = "",

        @Expose
        @SerializedName("cr_status")
        var cr_status: String = "",

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
        @Expose
        @SerializedName("cr_dtl_cost_code")
        var cost_code: String? = null,
)