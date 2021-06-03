package com.arbaelbarca.dagger2_with_retrofit_mvvm

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.arbaelbarca.dagger2_with_retrofit_mvvm.adapter.AdapterSearchUsersPaging
import com.arbaelbarca.dagger2_with_retrofit_mvvm.adapter.AdapterUsers
import com.arbaelbarca.dagger2_with_retrofit_mvvm.domain.response.ItemsItem
import com.arbaelbarca.dagger2_with_retrofit_mvvm.presentation.HomeFragment
import com.arbaelbarca.dagger2_with_retrofit_mvvm.utils.UiState
import com.arbaelbarca.dagger2_with_retrofit_mvvm.utils.hideView
import com.arbaelbarca.dagger2_with_retrofit_mvvm.utils.setRvAdapterVertikal
import com.arbaelbarca.dagger2_with_retrofit_mvvm.utils.showView
import com.arbaelbarca.dagger2_with_retrofit_mvvm.viewmodel.ViewModelMain
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    val viewmodelMain: ViewModelMain by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.frameMain, HomeFragment())
            .commit()

    }

}