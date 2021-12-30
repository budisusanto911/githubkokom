package com.example.kotlin.mvp.androidmvparchitexturekotlin.data.remote

import com.example.kotlin.mvp.androidmvparchitexturekotlin.data.remote.model.Home
import com.example.kotlin.mvp.androidmvparchitexturekotlin.data.remote.model.Login
import com.example.kotlin.mvp.androidmvparchitexturekotlin.data.remote.model.News
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