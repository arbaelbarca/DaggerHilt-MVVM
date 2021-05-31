package com.arbaelbarca.dagger2_with_retrofit_mvvm.repository

import com.arbaelbarca.dagger2_with_retrofit_mvvm.datasource.ApiService
import com.arbaelbarca.dagger2_with_retrofit_mvvm.domain.response.ResponseUsers
import javax.inject.Inject

class UserRepository @Inject constructor(
    val apiService: ApiService
) {
    suspend fun callListUser(): ResponseUsers {
        return apiService.callSearchUser("arba")
    }
}