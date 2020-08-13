package com.magdalena.movietoday.di

import com.google.gson.Gson
import com.magdalena.movietoday.BuildConfig
import com.magdalena.movietoday.manager.MovieManager
import com.magdalena.movietoday.network.MovieApi
import com.magdalena.movietoday.utils.API_KEY
import com.magdalena.movietoday.utils.BASE_URL
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideOkHttpClient() = OkHttpClient.Builder()
        .connectTimeout(5, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()


    @Singleton
    @Provides
    fun provideGson() = Gson()


    @Provides
    fun provideGoogleApi(gson: Gson?, okHttpClient: OkHttpClient): MovieApi {
        val httpClientBuilder = okHttpClient.newBuilder()
        httpClientBuilder.addInterceptor { chain ->
            val original = chain.request()

            val urlHttp =
                original.url().newBuilder().addQueryParameter("api_key", API_KEY).build()
            original.url().newBuilder().build()

            val request = original.newBuilder()
                .url(urlHttp)
                .build()
            chain.proceed(request)
        }
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            httpClientBuilder.addInterceptor(loggingInterceptor)
        }
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .callFactory(httpClientBuilder.build())
            .build().create(MovieApi::class.java)
    }

    @Provides
    fun providerMovieManger(api: MovieApi): MovieManager {
        return MovieManager(api)
    }

}