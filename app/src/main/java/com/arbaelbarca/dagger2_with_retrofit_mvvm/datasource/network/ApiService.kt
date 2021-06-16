package com.arbaelbarca.dagger2_with_retrofit_mvvm.datasource.network

import com.arbaelbarca.dagger2_with_retrofit_mvvm.domain.response.ResponseUsers
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/search/users")
    suspend fun callSearchUser(
        @Query("q") textSearch: String
    ): ResponseUsers

}