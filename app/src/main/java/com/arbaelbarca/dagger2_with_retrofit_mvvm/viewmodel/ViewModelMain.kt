package com.arbaelbarca.dagger2_with_retrofit_mvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arbaelbarca.dagger2_with_retrofit_mvvm.datasource.local.db.room.entity.EntityUser
import com.arbaelbarca.dagger2_with_retrofit_mvvm.domain.response.ResponseUsers
import com.arbaelbarca.dagger2_with_retrofit_mvvm.repository.UserRepository
import com.arbaelbarca.dagger2_with_retrofit_mvvm.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelMain @Inject constructor(
    val userRepository: UserRepository
) : ViewModel() {

    val stateUsers = MutableLiveData<UiState<ResponseUsers>>()
    val stateAddFav = MutableLiveData<UiState<String>>()
    val stateDeleteFav = MutableLiveData<UiState<String>>()
    val stateCheckFav = MutableLiveData<UiState<List<EntityUser>>>()
    val stateIsCheckFav = MutableLiveData<UiState<List<EntityUser>>>()

    fun observerUsers() = stateUsers
    fun observeAddFav() = stateAddFav
    fun observeDeleteFav() = stateDeleteFav
    fun observeCheckFav() = stateCheckFav
    fun observeIsCheckFav() = stateIsCheckFav

    fun checkFavUser(username: String) {
        viewModelScope.launch {
            runCatching {
                userRepository.checkFav(username)
            }.onSuccess {
                stateCheckFav.value = UiState.Success(it)
            }.onFailure {
                stateCheckFav.value = UiState.Failure(it)
            }
        }
    }

    fun ischeckFavUser(username: String) {
        viewModelScope.launch {
            runCatching {
                userRepository.checkFav(username)
            }.onSuccess {
                stateIsCheckFav.value = UiState.Success(it)
            }.onFailure {
                stateIsCheckFav.value = UiState.Failure(it)
            }
        }
    }

    fun addFavUser(entityUser: EntityUser) {
        viewModelScope.launch {
            runCatching {
                userRepository.addFavUser(entityUser)
            }.onSuccess {
                stateAddFav.value = UiState.Success("Berhasil nambah data fav")
            }.onFailure {
                stateAddFav.value = UiState.Failure(it)
            }
        }
    }

    fun deleteFavUser(entityUser: EntityUser) {
        viewModelScope.launch {
            runCatching {
                userRepository.deleteFavUser(entityUser)
            }.onSuccess {
                stateDeleteFav.value = UiState.Success("Terhapus data fav")
            }.onFailure {
                stateDeleteFav.value = UiState.Failure(it)
            }
        }
    }

    fun getListUser() {
        stateUsers.value = UiState.Loading()
        viewModelScope.launch {
            runCatching {
                userRepository.callListUser()
            }.onSuccess {
                stateUsers.value = UiState.Success(it)
            }.onFailure {
                stateUsers.value = UiState.Failure(it)
            }
        }
    }

}