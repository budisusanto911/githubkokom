package c.r.myapplication.data.remote.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.File

class UploadFotoRequest (
  @Expose @SerializedName("iduser") val idUser: Int
) {
  var file: File? = null
}