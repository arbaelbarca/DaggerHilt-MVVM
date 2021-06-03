package com.arbaelbarca.dagger2_with_retrofit_mvvm

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.arbaelbarca.dagger2_with_retrofit_mvvm.presentation.fragment.HomeFragment
import com.arbaelbarca.dagger2_with_retrofit_mvvm.viewmodel.ViewModelMain
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    val viewmodelMain: ViewModelMain by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(
                R.id.frameMain,
                HomeFragment()
            )
            .commit()

    }

}