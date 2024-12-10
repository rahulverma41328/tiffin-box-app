package com.example.tifinbox.helper


import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StoreUserData(private val context: Context) {

    private val Context.userDataStore: DataStore<Preferences> by preferencesDataStore(name = "user_data")

    private val dataStore = context.userDataStore

    companion object{
        val NAME = stringPreferencesKey("user_name")
        val PHONE = stringPreferencesKey("user_address")
        val ADDRESS = stringPreferencesKey("user_address")
        val ISLOGINED = booleanPreferencesKey("user_login")
    }

    suspend fun saveUserData(name:String,phone:String,address:String,isLogin:Boolean){
        dataStore.edit { preferences ->
            preferences[NAME] = name
            preferences[PHONE] = phone
            preferences[ADDRESS] = address
            preferences[ISLOGINED] = isLogin
         }
    }

    // Retrieve data from DataStore
    val userName: Flow<String> = dataStore.data
        .map { preferences -> preferences[NAME] ?: "" }

    val userPhone: Flow<String> = dataStore.data
        .map { preferences -> preferences[PHONE] ?: "" }

    val userAddress: Flow<String> = dataStore.data
        .map { preferences -> preferences[ADDRESS] ?: "" }

    val userLogin: Flow<Boolean> = dataStore.data
        .map { preferences -> preferences[ISLOGINED] ?: false}
}