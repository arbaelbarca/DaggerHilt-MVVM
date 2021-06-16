package com.arbaelbarca.dagger2_with_retrofit_mvvm.datasource.local.db.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.arbaelbarca.dagger2_with_retrofit_mvvm.datasource.local.db.room.entity.EntityUser


@Dao
interface UserDao : BaseDao<EntityUser> {
    @Query("SELECT * FROM tbl_users")
    suspend fun findAllUsers(): List<EntityUser>

    @Query("SELECT * FROM tbl_users WHERE username LIKE :titleUsers")
    suspend fun checkFavTitleUser(titleUsers: String): List<EntityUser>
}