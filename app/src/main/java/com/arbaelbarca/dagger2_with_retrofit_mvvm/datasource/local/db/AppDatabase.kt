package com.arbaelbarca.dagger2_with_retrofit_mvvm.datasource.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.arbaelbarca.dagger2_with_retrofit_mvvm.datasource.local.db.room.dao.UserDao
import com.arbaelbarca.dagger2_with_retrofit_mvvm.datasource.local.db.room.entity.EntityUser

@Database(
    entities = [EntityUser::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        AppDatabase::class.java,
                        "fav_users.db"
                    ).build()
                }
            }

            return INSTANCE as AppDatabase
        }
    }
}