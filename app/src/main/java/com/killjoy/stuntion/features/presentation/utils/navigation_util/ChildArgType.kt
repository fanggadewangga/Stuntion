package com.killjoy.stuntion.features.presentation.utils.navigation_util

import android.os.Bundle
import androidx.navigation.NavType
import com.google.gson.Gson
import com.killjoy.stuntion.features.domain.model.child.Child

@Suppress("DEPRECATION")
class ChildArgType : NavType<Child>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): Child? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): Child {
        return Gson().fromJson(value, Child::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: Child) {
        bundle.putParcelable(key, value)
    }
}