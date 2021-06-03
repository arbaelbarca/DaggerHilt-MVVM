package com.arbaelbarca.dagger2_with_retrofit_mvvm.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.arbaelbarca.dagger2_with_retrofit_mvvm.datasource.ApiService
import com.arbaelbarca.dagger2_with_retrofit_mvvm.domain.datasource.UsersPagingSource
import com.arbaelbarca.dagger2_with_retrofit_mvvm.domain.response.ResponseUsers
import javax.inject.Inject

class UserRepository @Inject constructor(
    val apiService: ApiService
) {
    fun getSearchResults(query: String) =
        Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { UsersPagingSource(apiService, query) }
        ).liveData
}