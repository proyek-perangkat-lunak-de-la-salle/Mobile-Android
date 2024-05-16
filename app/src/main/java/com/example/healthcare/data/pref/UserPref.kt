package com.example.healthcare.data.pref

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore("user_pref")


class UserPref private constructor(private val dataStore: DataStore<Preferences>) {

    private val TOKEN = stringPreferencesKey("token")
    private val ROLE = stringPreferencesKey("role")
    private val IS_LOGIN = booleanPreferencesKey("isLogin")

    fun getSession(): Flow<UserModel> {
        return dataStore.data.map {
            UserModel(
                it[TOKEN] ?: "",
                it[ROLE] ?: "",
                it[IS_LOGIN] ?: false
            )
        }
    }

    suspend fun saveSession(user: UserModel) {
        dataStore.edit {
            it[TOKEN] = user.token
            it[ROLE] = user.role
            it[IS_LOGIN] = user.isLogin
        }
    }

    suspend fun logout() {
        dataStore.edit {
            it.remove(TOKEN)
            it.remove(ROLE)
            it[IS_LOGIN] = false
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: UserPref? = null

        fun getInstance(dataStore: DataStore<Preferences>): UserPref {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = UserPref(dataStore)
                }
            }
            return INSTANCE as UserPref
        }
    }
}