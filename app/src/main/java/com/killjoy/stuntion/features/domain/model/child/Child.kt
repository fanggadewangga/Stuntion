package com.killjoy.stuntion.features.domain.model.child

import android.net.Uri
import android.os.Parcelable
import com.google.gson.Gson
import kotlinx.parcelize.Parcelize

@Parcelize
data class Child(
    val name: String,
    val gender: String,
    val birthDate: String,
    val height: Double,
    val weight: Double
): Parcelable {
    override fun toString(): String = Uri.encode(Gson().toJson(this))
}
