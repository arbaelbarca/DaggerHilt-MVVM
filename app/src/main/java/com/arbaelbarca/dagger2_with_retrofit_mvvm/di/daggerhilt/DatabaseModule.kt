package com.arbaelbarca.dagger2_with_retrofit_mvvm.di.daggerhilt

import android.content.Context
import com.arbaelbarca.dagger2_with_retrofit_mvvm.datasource.local.db.AppDatabase
import com.arbaelbarca.dagger2_with_retrofit_mvvm.datasource.local.db.room.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDao(appDatabase: AppDatabase): UserDao {
        return appDatabase.userDao()
    }


    @Singleton
    @Provides
    fun provideAppDatabse(@ApplicationContext appcontext: Context): AppDatabase {
        return AppDatabase.getInstance(appcontext)
    }

}