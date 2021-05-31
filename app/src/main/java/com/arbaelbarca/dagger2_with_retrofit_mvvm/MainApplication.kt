package com.arbaelbarca.dagger2_with_retrofit_mvvm

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApplication : Application() {


    override fun onCreate() {
        super.onCreate()

//        apiComponent = DaggerApiComponent.builder()
//            .appModuleApplication(AppModuleApplication(this))
//            .appModule(AppModule("https://api.github.com"))
//            .repositoryModule(RepositoryModule())
//            .viewModelModule(ViewModelModule())
//            .build()
    }
}