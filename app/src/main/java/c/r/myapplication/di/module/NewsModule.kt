package c.r.myapplication.di

import c.r.myapplication.data.local.LocalDataSource
import c.r.myapplication.data.remote.RemoteDataSource
import c.r.myapplication.di.scope.NewsScope
import c.r.myapplication.ui.add.AddPresenter
import c.r.myapplication.ui.adddetail.AdddetailPresenter
import c.r.myapplication.ui.detail.DetailPresenter
import c.r.myapplication.ui.home.HomePresenter
import c.r.myapplication.ui.home.gallery.GalleryPresenter
import c.r.myapplication.ui.home.item.HomeItemPresenter
import c.r.myapplication.ui.home.slideshow.SearchPresenter
import c.r.myapplication.ui.login.LoginPresenter
import c.r.myapplication.ui.news.NewsPresenter
import c.r.myapplication.ui.splash.SplashPresenter
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
  fun provideAddPresenter(localDataSource: LocalDataSource, remoteDataSource: RemoteDataSource): AddPresenter =
    AddPresenter(localDataSource, remoteDataSource)

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
  fun provideGalleryPresenter(localDataSource: LocalDataSource, remoteDataSource: RemoteDataSource): GalleryPresenter =
    GalleryPresenter(localDataSource, remoteDataSource)

  @Provides
  @NewsScope
  fun provideAdddetailPresenter(localDataSource: LocalDataSource, remoteDataSource: RemoteDataSource): AdddetailPresenter =
    AdddetailPresenter(localDataSource, remoteDataSource)

  @Provides
  @NewsScope
  fun providesearchPresenter(localDataSource: LocalDataSource, remoteDataSource: RemoteDataSource): SearchPresenter =
    SearchPresenter(localDataSource, remoteDataSource)
}