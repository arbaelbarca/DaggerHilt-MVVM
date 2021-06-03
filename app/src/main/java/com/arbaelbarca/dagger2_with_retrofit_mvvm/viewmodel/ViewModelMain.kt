package com.arbaelbarca.dagger2_with_retrofit_mvvm.viewmodel

import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.arbaelbarca.dagger2_with_retrofit_mvvm.domain.datasource.UsersPagingSource
import com.arbaelbarca.dagger2_with_retrofit_mvvm.domain.response.ItemsItem
import com.arbaelbarca.dagger2_with_retrofit_mvvm.domain.response.ResponseUsers
import com.arbaelbarca.dagger2_with_retrofit_mvvm.repository.UserRepository
import com.arbaelbarca.dagger2_with_retrofit_mvvm.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelMain @Inject constructor(val userRepository: UserRepository) : ViewModel() {

    companion object {
        private const val DEFAULT_QUERY = "arba"
    }

    private val currentQuery = MutableLiveData(DEFAULT_QUERY)
    val stateUsers = MutableLiveData<UiState<ResponseUsers>>()
    val stateLiveData = MutableLiveData<PagingData<ItemsItem>>()

    fun observerUsers() = stateUsers
    fun observerItems() = state

    val state = currentQuery.switchMap { queryString ->
        userRepository.getSearchResults(queryString).cachedIn(viewModelScope)
    }

    fun searchUsers(query: String) {
        currentQuery.value = query
    }

    fun getListUser() {
        stateUsers.value = UiState.Loading()
        viewModelScope.launch {
            runCatching {
                currentQuery.switchMap { queryString ->
                    userRepository.getSearchResults(queryString).cachedIn(viewModelScope)
                }
            }.onSuccess {
//                stateItems.value = UiState.Success(it)
            }.onFailure {
                stateUsers.value = UiState.Failure(it)
            }
        }
    }
}
