package com.arbaelbarca.dagger2_with_retrofit_mvvm.repository

import com.arbaelbarca.dagger2_with_retrofit_mvvm.datasource.local.db.room.dao.UserDao
import com.arbaelbarca.dagger2_with_retrofit_mvvm.datasource.local.db.room.entity.EntityUser
import com.arbaelbarca.dagger2_with_retrofit_mvvm.datasource.network.ApiService
import com.arbaelbarca.dagger2_with_retrofit_mvvm.domain.response.ResponseUsers
import javax.inject.Inject

class UserRepository @Inject constructor(
    val apiService: ApiService,
    val userDao: UserDao
) {
    suspend fun callListUser(): ResponseUsers {
        return apiService.callSearchUser("arba")
    }

    suspend fun addFavUser(entityUser: EntityUser) {
        return userDao.insert(entityUser)
    }

    suspend fun deleteFavUser(entityUser: EntityUser) {
        return userDao.delete(entityUser)
    }

    suspend fun checkFav(userName: String): List<EntityUser> {
        return userDao.checkFavTitleUser(userName)
    }
}