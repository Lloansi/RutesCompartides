package com.example.rutescompartidesapp.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.example.rutescompartidesapp.data.network.GoogleLocation.GoogleLocationApi
import com.example.rutescompartidesapp.data.network.idescat.idescatApi
import com.example.rutescompartidesapp.data.network.rutes_compartides.repository.RutesCompartidesRepository
import com.example.rutescompartidesapp.data.network.rutes_compartides.ApiRutesCompartides
import com.example.rutescompartidesapp.data.network.rutes_compartides.RutesCompartidesService
import com.example.rutescompartidesapp.utils.Constants
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideGoogleLocationApi(): GoogleLocationApi {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val gson = GsonBuilder()
            .serializeNulls()
            .create()

        val client = OkHttpClient
            .Builder()
            .addInterceptor(interceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(Constants.GOOGLE_MAPS_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideIdescatApi(): idescatApi {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val gson = GsonBuilder()
            .serializeNulls()
            .create()

        val client = OkHttpClient
            .Builder()
            .addInterceptor(interceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(Constants.IDESCAT_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideRutesCompartidesApi(): ApiRutesCompartides {

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val gson = GsonBuilder()
            .serializeNulls()
            .create()
        val client = OkHttpClient
            .Builder()
            .hostnameVerifier { hostname, session ->
            hostname == "dev.rutescompartides.cat"
            }
            .addInterceptor(interceptor)
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


    @Singleton
    @Provides
    fun provideDataStore(@ApplicationContext appContext: Context): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            corruptionHandler = ReplaceFileCorruptionHandler(produceNewData = { emptyPreferences() }),
            migrations = listOf(SharedPreferencesMigration(appContext, "session_prefs")),
            scope = CoroutineScope(Dispatchers.IO + SupervisorJob()),
            produceFile = { appContext.preferencesDataStoreFile("session_prefs") })
    }

}