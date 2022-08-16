package com.mingui.dallija.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.mingui.dallija.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import java.util.prefs.Preferences
import javax.inject.Inject

class AutoLoginRepository @Inject constructor(context: Context) {
    private val Context.dataStore by preferencesDataStore(name = "autologin_pref")

    private val dataStore = context.dataStore

    private object PreferencesKeys {
        val autoLogintState = booleanPreferencesKey(name = "auto_login_state" )
        val idKey = intPreferencesKey(name = "_id")
        val nameKey = stringPreferencesKey(name = "name")
        val emailKey = stringPreferencesKey(name = "email")
        val sexKey = intPreferencesKey(name = "sex")
        val heightKey = intPreferencesKey(name = "height")
        val profile_photo_path = stringPreferencesKey(name = "profile_photo_path")
    }

    suspend fun saveAutoLoginUser(user: User){
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.autoLogintState] = true
            preferences[PreferencesKeys.idKey] = user._id
            preferences[PreferencesKeys.nameKey] = user.name
            preferences[PreferencesKeys.emailKey] = user.email
            preferences[PreferencesKeys.sexKey] = user.sex
            preferences[PreferencesKeys.heightKey] = user.height
            preferences[PreferencesKeys.profile_photo_path] = user.profile_photo_path
        }
    }

    fun readAutoLoginState(): Flow<Boolean> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                val autoLoginState = preferences[PreferencesKeys.autoLogintState] ?: false
                autoLoginState
            }
    }

}