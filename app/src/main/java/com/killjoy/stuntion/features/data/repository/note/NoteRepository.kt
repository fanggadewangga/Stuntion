package com.killjoy.stuntion.features.data.repository.note

import android.util.Log
import com.killjoy.stuntion.features.data.source.remote.api.response.note.NoteBody
import com.killjoy.stuntion.features.data.source.remote.api.response.note.NoteResponse
import com.killjoy.stuntion.features.data.source.remote.api.service.StuntionApi
import com.killjoy.stuntion.features.data.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NoteRepository @Inject constructor(private val stuntionApi: StuntionApi) : INoteRepository {
    override suspend fun postNewNote(uid: String, body: NoteBody): Flow<Resource<String>> = flow {
        emit(Resource.Loading())
        try {
            val response = stuntionApi.postNewNote(uid, body)
            if (!response.isError) {
                Log.d("POST NOTE", response.message)
                emit(Resource.Success(response.message))
            } else
                emit(Resource.Error(response.message))
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun fetchNotesByUser(uid: String): Flow<Resource<List<NoteResponse>>> = flow {
        emit(Resource.Loading())
        try {
            val response = stuntionApi.fetchNoteByUser(uid)
            if (!response.isError) {
                Log.d("FETCH NOTES", response.message)
                emit(Resource.Success(response.data))
            } else
                emit(Resource.Error(response.message))
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun fetchNoteDetail(
        uid: String,
        noteId: String,
    ): Flow<Resource<NoteResponse>> = flow {
        emit(Resource.Loading())
        try {
            val response = stuntionApi.fetchNoteDetail(uid, noteId)
            if (!response.isError) {
                Log.d("NOTE DETAIL", response.message)
                emit(Resource.Success(response.data))
            } else
                emit(Resource.Error(response.message))
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)
}