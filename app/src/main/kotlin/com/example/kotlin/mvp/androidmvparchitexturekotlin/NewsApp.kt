package com.example.kotlin.mvp.androidmvparchitexturekotlin

import android.app.Application
import androidachitecturecomponent.example.di.AppComponent
import androidachitecturecomponent.example.di.AppModule
import androidachitecturecomponent.example.di.DaggerAppComponent
import com.example.kotlin.mvp.androidmvparchitexturekotlin.di.DaggerNewsComponent
import com.example.kotlin.mvp.androidmvparchitexturekotlin.di.NewsComponent
import com.example.kotlin.mvp.androidmvparchitexturekotlin.di.NewsModule

/**
 * Created by Handi Komara 12/2021.
 * handikomara22@gmail.com
 */

class NewsApp : Application() {

    companion object {

        var mNewsComponent : NewsComponent? = null
    }

    init {

        initializeDagger()
    }

    override fun onCreate() {
        super.onCreate()


    }

    /**
     * Initialize [AppComponent]
     *
     */
    private fun initializeDagger() {

        mNewsComponent = DaggerNewsComponent.builder().appComponent(DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()).newsModule(NewsModule()).build()

    }

}