package com.example.rutescompartidesapp.di

import com.example.rutescompartidesapp.data.network.gotify.GotifyApi
import com.example.rutescompartidesapp.data.network.openRouteService.OpenRouteServiceApi
import com.example.rutescompartidesapp.utils.Constants
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideOpenRouteApi(): OpenRouteServiceApi {
        val gson = GsonBuilder().serializeNulls().create()
        val okHttpClient = OkHttpClient.Builder().build()

        return Retrofit.Builder()
            .baseUrl(Constants.ORS_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create()
    }


    @Provides
    @Singleton
    fun provideGotifyServiceApi(): GotifyApi {
        val gson = GsonBuilder().serializeNulls().create()
        val okHttpClient = OkHttpClient.Builder().build()

        return Retrofit.Builder()
            .baseUrl(Constants.GOTIFY_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create()
    }
}