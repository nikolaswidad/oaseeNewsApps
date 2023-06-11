package com.nikolaswidad.oasenews.base

import android.app.Application
import com.nikolaswidad.oasenews.di.databaseModule
import com.nikolaswidad.oasenews.di.networkModule
import com.nikolaswidad.oasenews.di.repositoryModule
import com.nikolaswidad.oasenews.di.useCaseModule
import com.nikolaswidad.oasenews.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class NewsApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@NewsApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}