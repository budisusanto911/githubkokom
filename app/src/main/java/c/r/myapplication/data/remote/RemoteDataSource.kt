package c.r.myapplication.data.remote

import c.r.myapplication.data.remote.model.Home
import c.r.myapplication.data.remote.model.Login
import c.r.myapplication.data.remote.model.News
import c.r.myapplication.data.remote.model.Serializer
import c.r.myapplication.data.remote.model.Spinner
import c.r.myapplication.data.remote.model.UploadFotoRequest
import c.r.myapplication.data.remote.model.UploadResponse
import c.r.myapplication.utils.Const
import io.reactivex.Flowable
import io.reactivex.Observable
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response


class RemoteDataSource constructor(private val remoteService: RemoteService)  {

    fun getAriclesFromApi() : Flowable<Response<News>> = remoteService.getArticlesFromApi()
    fun login(user: String, password: String) : Flowable<Response<Login>> = remoteService.login(user, password)
    fun getHomeF(id: String) : Flowable<Response<Home>> = remoteService.getHomeF(id)
    fun getHome() : Flowable<Response<Home>> = remoteService.getHome()
    fun confirm(id: String,status: String) : Flowable<Response<Home>> = remoteService.confirm(id, status)
    fun halamancari(date1: String, date2: String) : Flowable<Response<Home>> = remoteService.halamancari(date1, date2)
    fun getKategori() : Flowable<Response<Spinner>> = remoteService.getKategori()
    fun getSubKategori(id: String) : Flowable<Response<Spinner>> = remoteService.getSubKategori(id)
    fun getPos(id: String) : Flowable<Response<Spinner>> = remoteService.getPos(id)
    fun getSubPos(id: String) : Flowable<Response<Spinner>> = remoteService.getSubPos(id)
    fun getCari(id: String, id1: String, id2: String, id3: String) : Flowable<Response<Home>> = remoteService.getCari(id,  id1, id2,  id3)
    fun getUraian(id: String, id1: String, id2: String, id3: String) : Flowable<Response<Spinner>> = remoteService.getUraian(id,  id1, id2,  id3)
    fun submit(id: String, id1: String, id2: String) : Flowable<Response<Login>> = remoteService.submit(id,  id1, id2)
    fun submitdetail(  cost_code: String,
        cr_id_hdr: String,
       cr_tanggal: String,
       cr_dtl_nominal: String,
        cr_uraian: String,
         cr_user: String
    ) : Flowable<Response<Login>> = remoteService.submitdetail(cost_code, cr_id_hdr, cr_tanggal, cr_dtl_nominal, cr_uraian, cr_user)
    fun uploadFoto(request: UploadFotoRequest): Observable<UploadResponse> {
        val parameter = RequestBody.create(
            MediaType.parse(Const.MEDIA_TYPE_TEXT_PLAIN),
            Serializer().serialize(request, UploadFotoRequest::class.java)
        )
        return if (request.file == null) {
            remoteService.uploadFoto(null, parameter)
        } else {
            val pictureRequestBody = RequestBody.create(
                MediaType.parse(Const.MEDIA_TYPE_MULTI_PART),
                request.file!!
            )
            val pictureImageParam = MultipartBody.Part.createFormData(
                "file",
                Const.generateAvatarFileName(),
                pictureRequestBody
            )
            remoteService.uploadFoto(pictureImageParam, parameter)
        }
    }

}