package com.killjoy.stuntion.features.presentation.screen.avatar

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.killjoy.stuntion.features.data.repository.user.UserRepository
import com.killjoy.stuntion.features.data.util.Resource
import com.killjoy.stuntion.features.presentation.utils.Avatar
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AvatarViewModel @Inject constructor(private val repository: UserRepository) :
    ViewModel() {

    private val _updateAvatarResponse = MutableStateFlow<Resource<String?>>(Resource.Empty())
    val updateAvatarResponse = _updateAvatarResponse.asStateFlow()

    val listOfAvatar = listOf(
        Avatar(
            id = "avatar_1",
            avatarUrl = "https://firebasestorage.googleapis.com/v0/b/stuntion-a32cc.appspot.com/o/avatar%2Favatar_1.png?alt=media&token=931a191d-6277-4480-b860-fdf8e0e41dfe"
        ),
        Avatar(
            id = "avatar_2",
            avatarUrl = "https://firebasestorage.googleapis.com/v0/b/stuntion-a32cc.appspot.com/o/avatar%2Favatar_2.png?alt=media&token=0ecb75d0-1fad-464e-b265-42bca28edf89"
        ),
        Avatar(
            id = "avatar_3",
            avatarUrl = "https://firebasestorage.googleapis.com/v0/b/stuntion-a32cc.appspot.com/o/avatar%2Favatar_3.png?alt=media&token=45669833-e5a4-4080-b432-332fb0f44400"
        ),
        Avatar(
            id = "avatar_4",
            avatarUrl = "https://firebasestorage.googleapis.com/v0/b/stuntion-a32cc.appspot.com/o/avatar%2Favatar_4.png?alt=media&token=e7b0aabb-3452-409e-9d37-d34f78fe92b6"
        ),
        Avatar(
            id = "avatar_5",
            avatarUrl = "https://firebasestorage.googleapis.com/v0/b/stuntion-a32cc.appspot.com/o/avatar%2Favatar_5.png?alt=media&token=cd2b8221-85d3-425e-be73-487a73a7007e"
        ),
        Avatar(
            id = "avatar_6",
            avatarUrl = "https://firebasestorage.googleapis.com/v0/b/stuntion-a32cc.appspot.com/o/avatar%2Favatar_6.png?alt=media&token=799ebf5b-1584-474b-b155-4e97949c0422"
        ),
        Avatar(
            id = "avatar_7",
            avatarUrl = "https://firebasestorage.googleapis.com/v0/b/stuntion-a32cc.appspot.com/o/avatar%2Favatar_7.png?alt=media&token=532b44b4-57c5-49ee-987a-14c270093033"
        ),
        Avatar(
            id = "avatar_8",
            avatarUrl = "https://firebasestorage.googleapis.com/v0/b/stuntion-a32cc.appspot.com/o/avatar%2Favatar_8.png?alt=media&token=797d3b12-f380-4055-9442-f10f639b7e26"
        ),
        Avatar(
            id = "avatar_9",
            avatarUrl = "https://firebasestorage.googleapis.com/v0/b/stuntion-a32cc.appspot.com/o/avatar%2Favatar_9.png?alt=media&token=83a66afa-3900-40bd-8feb-267348817ece"
        ),
    )

    val selectedAvatar = mutableStateOf(listOfAvatar[0])

    fun updateUserAvatar() {
        viewModelScope.launch {
            val uid = repository.readUid().first()!!
            repository.updateUserAvatar(uid, selectedAvatar.value.avatarUrl).collect {
                _updateAvatarResponse.value = it
            }
        }
    }
}