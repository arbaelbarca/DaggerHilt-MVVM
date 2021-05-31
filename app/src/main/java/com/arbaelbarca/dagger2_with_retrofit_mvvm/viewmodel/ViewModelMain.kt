package com.arbaelbarca.dagger2_with_retrofit_mvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arbaelbarca.dagger2_with_retrofit_mvvm.domain.response.ResponseUsers
import com.arbaelbarca.dagger2_with_retrofit_mvvm.repository.UserRepository
import com.arbaelbarca.dagger2_with_retrofit_mvvm.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelMain @Inject constructor(val userRepository: UserRepository) : ViewModel() {

    val stateUsers = MutableLiveData<UiState<ResponseUsers>>()

//    lateinit var userRepository: UserRepository

    fun observerUsers() = stateUsers

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