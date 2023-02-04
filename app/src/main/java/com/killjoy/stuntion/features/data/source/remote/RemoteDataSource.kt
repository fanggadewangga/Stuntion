package com.killjoy.stuntion.features.data.source.remote

import com.killjoy.stuntion.features.data.source.remote.firebase.FirebaseDataSource
import java.util.concurrent.Flow
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    // TODO : Inject API Source
    private val FirebaseDataSource: FirebaseDataSource,
) {

}