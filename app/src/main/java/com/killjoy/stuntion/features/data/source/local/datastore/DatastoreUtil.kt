package com.killjoy.stuntion.features.data.source.local.datastore

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object DatastoreUtil {
    const val DATASTORE_NAME = "STUNTION_DATASTORE"
    val HAVE_RUN_APP_BEFORE_PREF_KEY = booleanPreferencesKey("isFirstTime")
    val UID_PREF_KEY = stringPreferencesKey("uid")
    val HAVE_UPDATE_GENERAL_INFO_PREF_KEY = booleanPreferencesKey("isHaveUpdate")
    val HAVE_CREATE_ACCOUNT_SUCCESSFULLY = booleanPreferencesKey("isAccountCreated")
    val REGISTER_PROGRESS_PREF_KEY = intPreferencesKey("register")
}