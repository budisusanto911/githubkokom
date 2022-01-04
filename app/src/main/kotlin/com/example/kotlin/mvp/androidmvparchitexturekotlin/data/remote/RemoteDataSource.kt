package com.example.kotlin.mvp.androidmvparchitexturekotlin.data.remote

import com.example.kotlin.mvp.androidmvparchitexturekotlin.data.remote.model.Home
import com.example.kotlin.mvp.androidmvparchitexturekotlin.data.remote.model.Login
import com.example.kotlin.mvp.androidmvparchitexturekotlin.data.remote.model.News
import com.example.kotlin.mvp.androidmvparchitexturekotlin.data.remote.model.Spinner
import io.reactivex.Flowable
import retrofit2.Response

/**
 * Created by Ali DOUIRI on 27/04/2018.
 * my.alidouiri@gmail.com
 */

class RemoteDataSource constructor(private val remoteService: RemoteService)  {

    fun getAriclesFromApi() : Flowable<Response<News>> = remoteService.getArticlesFromApi()
    fun login(user: String, password: String) : Flowable<Response<Login>> = remoteService.login(user, password)
    fun getHomeF(id: String) : Flowable<Response<Home>> = remoteService.getHomeF(id)
    fun getHome() : Flowable<Response<Home>> = remoteService.getHome()
    fun getKategori() : Flowable<Response<Spinner>> = remoteService.getKategori()
    fun getSubKategori(id: String) : Flowable<Response<Spinner>> = remoteService.getSubKategori(id)
    fun getPos(id: String) : Flowable<Response<Spinner>> = remoteService.getPos(id)
    fun getSubPos(id: String) : Flowable<Response<Spinner>> = remoteService.getSubPos(id)
    fun getCari(id: String, id1: String, id2: String, id3: String) : Flowable<Response<Home>> = remoteService.getCari(id,  id1, id2,  id3)
    fun getUraian(id: String, id1: String, id2: String, id3: String) : Flowable<Response<Spinner>> = remoteService.getUraian(id,  id1, id2,  id3)

}