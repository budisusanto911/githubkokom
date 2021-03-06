package c.r.myapplication.di.module.co

import android.app.Application
import android.content.Context
import androidx.room.Room
import c.r.myapplication.BuildConfig
import c.r.myapplication.data.local.LocalDataSource
import c.r.myapplication.data.local.dao.ArticleDao
import c.r.myapplication.data.remote.RemoteDataSource
import c.r.myapplication.data.remote.RemoteService
import c.r.myapplication.utils.Constants
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


/**
 * Created by DOUIRI Ali on 16/03/2018.
 * handikomara22@gmail.com
 */

@Module
class AppModule {

    var mApplication : Application?= null

    constructor(application : Application) {

        mApplication = application
    }

    @Singleton
    @Provides
    fun providesLocalDataSource(): LocalDataSource {
        return Room.databaseBuilder(mApplication!!, LocalDataSource::class.java, "news_database")
                .build()
    }

    /*@Singleton
    @Provides
    fun providesArticleDao(userRoomDatabase: LocalDataSource): ArticleDao {
        return userRoomDatabase.getArticleDao()
    }*/

    @Provides
    fun providesAppContext(): Context {
        return mApplication!!
    }

    @Provides
    @Singleton
    fun providesRemoteService(): RemoteService {
        return Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(getOkHttpClient())
                .build()
                .create(RemoteService::class.java)
    }

    private fun getOkHttpClient(): OkHttpClient {

        return OkHttpClient.Builder()
                .addInterceptor { chain ->
                    val original = chain.request()

                    val originalHttpUrl = original.url()

                    val mUrl = originalHttpUrl.newBuilder()
                            .addQueryParameter(Constants.NAME_KEY_API_NEWS, BuildConfig.VALUE_KEY_API_NEWS)
                            .addQueryParameter(Constants.NAME_COUNTRY_API_NEWS, Constants.VALUE_COUNTRY_API_NEWS)
                            .build()


                    val request = original.newBuilder()
                            .header("Content-Type", "application/json")
                            .url(mUrl)
                            .build()


                    val response = chain.proceed(request)
                    response.cacheResponse()
                    response
                }
                .readTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .build()
    }

    @Provides
    @Singleton
    fun providesRemoteDataSource(remoteService: RemoteService): RemoteDataSource {
        return RemoteDataSource(remoteService)
    }

}