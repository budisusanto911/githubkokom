package c.r.myapplication

import android.app.Application
import c.r.myapplication.di.DaggerNewsComponent
import c.r.myapplication.di.NewsComponent
import c.r.myapplication.di.NewsModule
import c.r.myapplication.di.component.co.DaggerAppComponent
import c.r.myapplication.di.module.co.AppModule


/**
 * Created by Handi Komara.
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