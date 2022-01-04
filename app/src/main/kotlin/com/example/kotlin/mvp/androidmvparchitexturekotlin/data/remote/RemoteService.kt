package com.example.kotlin.mvp.androidmvparchitexturekotlin.data.remote

import com.example.kotlin.mvp.androidmvparchitexturekotlin.data.remote.model.Home
import com.example.kotlin.mvp.androidmvparchitexturekotlin.data.remote.model.Login
import com.example.kotlin.mvp.androidmvparchitexturekotlin.data.remote.model.News
import com.example.kotlin.mvp.androidmvparchitexturekotlin.data.remote.model.Spinner
import io.reactivex.Flowable
import retrofit2.Response
import retrofit2.http.GET
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

    @GET(".")
    fun getArticlesFromApi() : Flowable<Response<News>>

    @GET("login")
    fun login(
        @Query("user") user: String,
        @Query("password") password: String
    ) : Flowable<Response<Login>>
}