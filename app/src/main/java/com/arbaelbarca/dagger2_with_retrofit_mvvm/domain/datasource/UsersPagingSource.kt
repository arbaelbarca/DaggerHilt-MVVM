package com.arbaelbarca.dagger2_with_retrofit_mvvm.domain.datasource

import androidx.paging.PagingSource
import com.arbaelbarca.dagger2_with_retrofit_mvvm.datasource.ApiService
import com.arbaelbarca.dagger2_with_retrofit_mvvm.domain.response.ItemsItem
import retrofit2.HttpException
import java.io.IOException

private const val USER_STARTING_PAGE_INDEX = 1

class UsersPagingSource(
    val apiService: ApiService,
    val query: String
) : PagingSource<Int, ItemsItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ItemsItem> {
        val position = params.key ?: USER_STARTING_PAGE_INDEX

        return try {
            val response = apiService.callSearchUser(query, position, params.loadSize)
            val itemsItem = response.items

            LoadResult.Page(
                data = itemsItem,
                prevKey = if (position == USER_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (itemsItem.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

}