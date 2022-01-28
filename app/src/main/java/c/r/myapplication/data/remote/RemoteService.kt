package c.r.myapplication.data.remote

import c.r.myapplication.data.remote.model.Home
import c.r.myapplication.data.remote.model.Login
import c.r.myapplication.data.remote.model.News
import c.r.myapplication.data.remote.model.Spinner
import c.r.myapplication.data.remote.model.UploadResponse
import io.reactivex.Flowable
import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

/**
 * Created by Ali DOUIRI on 27/04/2018.
 * my.alidouiri@gmail.com
 */

interface RemoteService {


    @GET("home")
    fun getHome() : Flowable<Response<Home>>

    @GET("kategori")
    fun getKategori() : Flowable<Response<Spinner>>

    @GET("sub_kategori")
    fun getSubKategori(
        @Query("kategori") id: String,
    ) : Flowable<Response<Spinner>>

    @GET("pos")
    fun getPos(
        @Query("induk") id: String,
    ) : Flowable<Response<Spinner>>

    @GET("sub_pos")
    fun getSubPos(
        @Query("cabang") id: String,
    ) : Flowable<Response<Spinner>>

    @GET("cari")
    fun getCari(
        @Query("project") project: String,
        @Query("induk") induk: String,
        @Query("cabang") cabang: String,
        @Query("ranting") ranting: String,
    ) : Flowable<Response<Home>>

    @GET("uraian")
    fun getUraian(
        @Query("project") project: String,
        @Query("induk") induk: String,
        @Query("cabang") cabang: String,
        @Query("ranting") ranting: String,
    ) : Flowable<Response<Spinner>>

    @GET("homef")
    fun getHomeF(
        @Query("id") id: String,
    ) : Flowable<Response<Home>>

    @FormUrlEncoded
    @POST("updateStatus")
    fun confirm(
        @Field("id")  id: String,
        @Field("status")  status: String,
    ) : Flowable<Response<Home>>

    @GET(".")
    fun getArticlesFromApi() : Flowable<Response<News>>

    @GET("login")
    fun login(
        @Query("user") user: String,
        @Query("password") password: String
    ) : Flowable<Response<Login>>

    @GET("halamancari")
    fun halamancari(
        @Query("date1") user: String,
        @Query("date2") password: String
    ) : Flowable<Response<Home>>

    @FormUrlEncoded
    @POST("submit1")
    fun submit(
        @Field("cr_no_hdr")  no: String,
        @Field("cr_foto")  foto: String,
        @Field("cr_tanggal")  tanggal: String
    ) : Flowable<Response<Login>>

    @FormUrlEncoded
    @POST("submitdetail")
    fun submitdetail(
        @Field("cost_code")  cost_code: String,
        @Field("cr_id_hdr")  cr_id_hdr: String,
        @Field("cr_tanggal")  cr_tanggal: String,
        @Field("cr_dtl_nominal")  cr_dtl_nominal: String,
        @Field("cr_uraian")  cr_uraian: String,
        @Field("cr_user")  cr_user: String
    ) : Flowable<Response<Login>>

    @Multipart
    @POST("uploadfoto")
    fun uploadFoto(
        @Part file: MultipartBody.Part?,
        @Part("iduser") parameter: RequestBody
    ): Observable<UploadResponse>
}