package c.r.myapplication.di.component.co

import c.r.myapplication.data.local.LocalDataSource
import c.r.myapplication.data.remote.RemoteDataSource
import c.r.myapplication.di.module.co.AppModule
import dagger.Component
import javax.inject.Singleton


/**
 * Created by DOUIRI Ali on 16/03/2018.
 * my.alidouiri@gmail.com
 */

@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {

    fun roomDataSource(): LocalDataSource

    fun remoteDataSource(): RemoteDataSource

}