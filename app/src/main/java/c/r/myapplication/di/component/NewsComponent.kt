package c.r.myapplication.di

import c.r.myapplication.di.component.co.AppComponent
import c.r.myapplication.di.scope.NewsScope
import c.r.myapplication.ui.add.AddActivity
import c.r.myapplication.ui.adddetail.AddDetailActivity
import c.r.myapplication.ui.detail.DetailActivity
import c.r.myapplication.ui.home.gallery.GalleryFragment
import c.r.myapplication.ui.home.home
import c.r.myapplication.ui.home.item.HomeFragment
import c.r.myapplication.ui.home.slideshow.SlideshowFragment
import c.r.myapplication.ui.login.LoginActivity
import c.r.myapplication.ui.news.NewsActivity
import c.r.myapplication.ui.splash.SplashActivity
import dagger.Component

/**
 * Created by Ali DOUIRI on 27/04/2018.
 * my.alidouiri@gmail.com
 */

@NewsScope
@Component(modules = arrayOf(NewsModule::class), dependencies = arrayOf(AppComponent::class))
interface NewsComponent {
    fun inject(view: SlideshowFragment)
    fun inject(view: home)
    fun inject(view: AddDetailActivity)
    fun inject(view: LoginActivity)
    fun inject(view: NewsActivity)
    fun inject(view: SplashActivity)
    fun inject(view: HomeFragment)
    fun inject(view: GalleryFragment)
    fun inject(view: AddActivity)
    fun inject(view: DetailActivity)
}