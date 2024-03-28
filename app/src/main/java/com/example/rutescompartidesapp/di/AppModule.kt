package com.example.rutescompartidesapp.di

import com.example.rutescompartidesapp.data.network.rutes_compartides.repository.RutesCompartidesRepository
import com.example.rutescompartidesapp.data.network.rutes_compartides.ApiRutesCompartides
import com.example.rutescompartidesapp.data.network.rutes_compartides.RutesCompartidesService
import com.example.rutescompartidesapp.utils.Constants
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /*
    @Provides
    @Singleton
    fun provideChatApi(): WebSocket {

        val client = OkHttpClient.Builder()
            .build()

        val request = Request.Builder()
            .url("ws://localhost/chatbueno")
            .build()

        return client.newWebSocket(request, ) //TODO Falta añadir el WebSocketListener, que provee de las funciones basicas de un websocket
    }

     */


    @Provides
    @Singleton
    fun provideRutesCompartidesApi(): ApiRutesCompartides {
        val gson = GsonBuilder()
            .serializeNulls()
            .create()
        val client = OkHttpClient.Builder()
            .build()

        return Retrofit.Builder()
            .baseUrl(Constants.RUTES_COMPARTIDES_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
            .create()
    }


    @Provides
    @Singleton
    fun providesRutesCompartidesRepository(apiRutesCompartides: ApiRutesCompartides): RutesCompartidesService {
        return RutesCompartidesRepository(apiRutesCompartides)
    }

}