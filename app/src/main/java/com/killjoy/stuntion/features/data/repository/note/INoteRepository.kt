package com.killjoy.stuntion.features.data.repository.note

import com.killjoy.stuntion.features.data.source.remote.api.response.note.NoteBody
import com.killjoy.stuntion.features.data.source.remote.api.response.note.NoteResponse
import com.killjoy.stuntion.features.data.util.Resource
import kotlinx.coroutines.flow.Flow

interface INoteRepository {
    suspend fun postNewNote(uid: String, body: NoteBody): Flow<Resource<String>>
    suspend fun fetchNotesByUser(uid: String): Flow<Resource<List<NoteResponse>>>
    suspend fun fetchNoteDetail(uid: String, noteId: String): Flow<Resource<NoteResponse>>
}