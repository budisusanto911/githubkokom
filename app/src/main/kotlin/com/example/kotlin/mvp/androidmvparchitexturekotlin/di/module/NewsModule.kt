package com.example.kotlin.mvp.androidmvparchitexturekotlin.di

import com.example.kotlin.mvp.androidmvparchitexturekotlin.data.local.LocalDataSource
import com.example.kotlin.mvp.androidmvparchitexturekotlin.data.remote.RemoteDataSource
import com.example.kotlin.mvp.androidmvparchitexturekotlin.di.scope.NewsScope
import com.example.kotlin.mvp.androidmvparchitexturekotlin.ui.add.AddPresenter
import com.example.kotlin.mvp.androidmvparchitexturekotlin.ui.detail.DetailPresenter
import com.example.kotlin.mvp.androidmvparchitexturekotlin.ui.home.HomePresenter
import com.example.kotlin.mvp.androidmvparchitexturekotlin.ui.home.gallery.GalleryPresenter
import com.example.kotlin.mvp.androidmvparchitexturekotlin.ui.home.item.HomeItemPresenter
import com.example.kotlin.mvp.androidmvparchitexturekotlin.ui.login.LoginPresenter
import com.example.kotlin.mvp.androidmvparchitexturekotlin.ui.news.NewsPresenter
import com.example.kotlin.mvp.androidmvparchitexturekotlin.ui.splash.SplashPresenter
import dagger.Module
import dagger.Provides

/**
 * Created by Ali DOUIRI on 27/04/2018.
 * my.alidouiri@gmail.com
 */

@Module
class NewsModule {

    @Provides
    @NewsScope
    fun providesNewsPresenter(localDataSource: LocalDataSource, remoteDataSource: RemoteDataSource): NewsPresenter =
            NewsPresenter(localDataSource, remoteDataSource)
    @Provides
    @NewsScope
    fun providesSplashPresenter(localDataSource: LocalDataSource, remoteDataSource: RemoteDataSource): SplashPresenter =
      SplashPresenter(localDataSource, remoteDataSource)
    @Provides
    @NewsScope
    fun provideLoginPresenter(localDataSource: LocalDataSource, remoteDataSource: RemoteDataSource): LoginPresenter =
      LoginPresenter(localDataSource, remoteDataSource)

  @Provides
    @NewsScope
    fun provideHomePresenter(localDataSource: LocalDataSource, remoteDataSource: RemoteDataSource): HomePresenter =
      HomePresenter(localDataSource, remoteDataSource)

  @Provides
  @NewsScope
  fun provideHomeItemPresenter(localDataSource: LocalDataSource, remoteDataSource: RemoteDataSource): HomeItemPresenter =
    HomeItemPresenter(localDataSource, remoteDataSource)

  @Provides
  @NewsScope
  fun provideDetailPresenter(localDataSource: LocalDataSource, remoteDataSource: RemoteDataSource): DetailPresenter =
    DetailPresenter(localDataSource, remoteDataSource)

  @Provides
  @NewsScope
  fun provideAddPresenter(localDataSource: LocalDataSource, remoteDataSource: RemoteDataSource): AddPresenter =
    AddPresenter(localDataSource, remoteDataSource)

  @Provides
  @NewsScope
  fun provideGalleryPresenter(localDataSource: LocalDataSource, remoteDataSource: RemoteDataSource): GalleryPresenter =
    GalleryPresenter(localDataSource, remoteDataSource)
}