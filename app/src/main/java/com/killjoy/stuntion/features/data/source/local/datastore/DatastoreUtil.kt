package com.killjoy.stuntion.features.data.source.local.datastore

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object DatastoreUtil {
    const val DATASTORE_NAME = "STUNTION_DATASTORE"
    val HAVE_RUN_APP_BEFORE_PREF_KEY = booleanPreferencesKey("isFirstTime")
    val UID_PREF_KEY = stringPreferencesKey("uid")
}