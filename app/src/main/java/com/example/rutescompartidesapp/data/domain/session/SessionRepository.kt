package com.example.rutescompartidesapp.data.domain.session

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject
class SessionRepository @Inject constructor(private val dataStore: DataStore<Preferences>) {

    private val TAG = "Session"
    private object SessionKeys{
        val IS_LOGGED = booleanPreferencesKey("is_logged")
        val EMAIL = stringPreferencesKey("email")
        val PASSWORD = stringPreferencesKey("password")
       //val email = stringPreferencesKey(EMAIL)
        //val isLogin = booleanPreferencesKey(IS_LOGIN)
        //val password = stringPreferencesKey(PASSWORD)
    }

    private fun mapSessionPreferences(preferences: Preferences): Session {
        // Get our show completed value, defaulting to false or "" if not set:
        val isLogged =  preferences[SessionKeys.IS_LOGGED] ?: false
        val email = preferences[SessionKeys.EMAIL] ?: ""
        val password = preferences[SessionKeys.PASSWORD] ?: ""

        return Session(isLogged, email, password)
    }


    val sessionPreferencesFlow: Flow<Session> = dataStore.data
        .catch { exception ->
            // dataStore.data throws an IOException when an error is encountered when reading data
            if (exception is IOException) {
                Log.e(TAG, "Error reading preferences.", exception)
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            mapSessionPreferences(preferences)
        }


    // Update values
    suspend fun updateIsLogged(isLogged: Boolean) {
        dataStore.edit { preferences ->
            preferences[SessionKeys.IS_LOGGED] = isLogged
        }
    }
    suspend fun updateEmail(email: String) {
        dataStore.edit { preferences ->
            preferences[SessionKeys.EMAIL] = email
        }
    }
    suspend fun updatePassword(password: String) {
        dataStore.edit { preferences ->
            preferences[SessionKeys.PASSWORD] = password
        }
    }

    suspend fun fetchInitialPreferences() =
        mapSessionPreferences(dataStore.data.first().toPreferences())

}