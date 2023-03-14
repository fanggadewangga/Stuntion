package com.killjoy.stuntion.features.data.source.remote.firebase

import android.net.Uri
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseDataSource @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseStorage: FirebaseStorage,
    private val firebaseMessaging: FirebaseMessaging,
) {
    fun createUserWithEmailAndPassword(
        email: String,
        password: String,
    ): Flow<FirebaseResponse<String>> = flow {
        val createUser = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
        val user = createUser.user
        if (user != null)
            emit(FirebaseResponse.Success(user.uid))
        else
            emit(FirebaseResponse.Empty)
    }.catch {
        emit(FirebaseResponse.Error(it.message.toString()))
    }.flowOn(Dispatchers.IO)

    fun signInWithEmailAndPassword(
        email: String,
        password: String,
    ): Flow<FirebaseResponse<String>> = flow {
        val signInUser = firebaseAuth.signInWithEmailAndPassword(email, password).await()
        val user = signInUser.user
        if (user != null)
            emit(FirebaseResponse.Success(user.uid))
        else
            emit(FirebaseResponse.Empty)
    }.catch {
        emit(FirebaseResponse.Error(it.message.toString()))
    }.flowOn(Dispatchers.IO)

    fun getAvatar(): Flow<FirebaseResponse<Uri>> = flow {
        val avatar = firebaseStorage.reference.child("avatar/avatar_9.png").downloadUrl.await()
        if (avatar != null) {
            emit(FirebaseResponse.Success(avatar))
            Log.d("GET AVATAR : ", "Masuk success $avatar")
        } else
            emit(FirebaseResponse.Empty)
    }.catch {
        emit(FirebaseResponse.Error(it.message.toString()))
    }

    fun logout(): Flow<FirebaseResponse<String>> = flow {
        try {
            firebaseAuth.signOut()
            emit(FirebaseResponse.Success("Logout Success!"))
        } catch (e: Exception) {
            emit(FirebaseResponse.Error(e.message.toString()))
        }
    }
}