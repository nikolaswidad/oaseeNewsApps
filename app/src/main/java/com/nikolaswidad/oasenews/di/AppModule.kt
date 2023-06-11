package com.nikolaswidad.oasenews.di

import androidx.room.Room
import com.nikolaswidad.oasenews.BuildConfig
import com.nikolaswidad.oasenews.datasource.INewsRepository
import com.nikolaswidad.oasenews.datasource.NewsRepository
import com.nikolaswidad.oasenews.datasource.local.LocalDataSource
import com.nikolaswidad.oasenews.datasource.local.database.NewsDatabase
import com.nikolaswidad.oasenews.datasource.remote.RemoteDataSource
import com.nikolaswidad.oasenews.datasource.remote.api.ApiClient
import com.nikolaswidad.oasenews.domain.NewsInteractor
import com.nikolaswidad.oasenews.domain.NewsUseCase
import com.nikolaswidad.oasenews.ui.screen.main.MainViewModel
import com.nikolaswidad.oasenews.utils.Config.BASE_URL
import com.nikolaswidad.oasenews.utils.Config.DATABASE_NAME
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<NewsDatabase>().newsDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            NewsDatabase::class.java,
            DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().setLevel(
                    if (BuildConfig.DEBUG) {
                        HttpLoggingInterceptor.Level.BODY
                    } else HttpLoggingInterceptor.Level.NONE
                )
            )
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiClient::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    single<INewsRepository> { NewsRepository(get(), get()) }
}

val useCaseModule = module {
    factory<NewsUseCase> { NewsInteractor(get()) }
}

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
}