package id.tomuchcoffee.newsapp

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import id.tomuchcoffee.newsapp.source.network.networkModule
import id.tomuchcoffee.newsapp.source.news.repositoryModule
import id.tomuchcoffee.newsapp.source.persistence.databaseModule
import id.tomuchcoffee.newsapp.ui.bookmark.bookmarkModule
import id.tomuchcoffee.newsapp.ui.bookmark.bookmarkViewModel
import id.tomuchcoffee.newsapp.ui.detail.detailModul
import id.tomuchcoffee.newsapp.ui.detail.detailViewModel
import id.tomuchcoffee.newsapp.ui.home.homeModule
import id.tomuchcoffee.newsapp.ui.home.homeViewModel
import timber.log.Timber
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class NewsApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        Timber.e("run base application")
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        startKoin {
//            androidLogger()
//            androidContext(this@NewsApp)
            androidLogger(Level.ERROR)
            androidContext(this@NewsApp)
            modules(
                networkModule,
                repositoryModule,
                homeViewModel,
                homeModule,
                bookmarkViewModel,
                bookmarkModule,
                databaseModule,
                detailViewModel,
                detailModul

            )
        }
    }
}