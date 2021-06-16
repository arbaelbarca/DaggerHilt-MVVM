package com.arbaelbarca.dagger2_with_retrofit_mvvm.datasource.local.db.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_users")
class EntityUser(
    @PrimaryKey
    var id: Int,

    @ColumnInfo(name = "username")
    var username: String,

    @ColumnInfo(name = "imageUser")
    var imageUser: String
)