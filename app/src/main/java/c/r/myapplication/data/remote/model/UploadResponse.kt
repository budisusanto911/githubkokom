package c.r.myapplication.data.remote.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UploadResponse(
  @Expose @SerializedName("files") val files: String?,
  @Expose @SerializedName("url") val url: String?
)