package com.killjoy.stuntion.features.data.source.local.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map
import javax.inject.Inject

val Context.rootDataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class StuntionDatastore @Inject constructor(context: Context) {
    private val stuntionDatastore = context.rootDataStore

    suspend fun savePrefHaveRunAppBefore(isFirstTime: Boolean) {
        stuntionDatastore.edit {
            it[DatastoreUtil.HAVE_RUN_APP_BEFORE_PREF_KEY] = isFirstTime
        }
    }

    suspend fun savePrefUid(uid: String) {
        stuntionDatastore.edit {
            it[DatastoreUtil.UID_PREF_KEY] = uid
        }
    }

    suspend fun savePrefHaveUpdateGeneralInfo(isHaveUpdateGeneralInfo: Boolean) {
        stuntionDatastore.edit {
            it[DatastoreUtil.HAVE_UPDATE_GENERAL_INFO_PREF_KEY] = isHaveUpdateGeneralInfo
        }
    }

    suspend fun savePrefHaveCreatedAccount(isCreatedSuccessfully: Boolean) {
        stuntionDatastore.edit {
            it[DatastoreUtil.HAVE_CREATE_ACCOUNT_SUCCESSFULLY] = isCreatedSuccessfully
        }
    }

    fun readPrefHaveRunAppBefore() = stuntionDatastore.data.map {
        it[DatastoreUtil.HAVE_RUN_APP_BEFORE_PREF_KEY] ?: false
    }

    fun readPrevUid() = stuntionDatastore.data.map {
        it[DatastoreUtil.UID_PREF_KEY]
    }

    fun readPrefHaveEditGeneralInfo() = stuntionDatastore.data.map {
        it[DatastoreUtil.HAVE_UPDATE_GENERAL_INFO_PREF_KEY] ?: false
    }

    fun readPrefHaveCreatedAccount() = stuntionDatastore.data.map {
        it[DatastoreUtil.HAVE_CREATE_ACCOUNT_SUCCESSFULLY] ?: false
    }

    suspend fun deleteUid() = stuntionDatastore.edit {
        it.remove(DatastoreUtil.UID_PREF_KEY)
    }
}