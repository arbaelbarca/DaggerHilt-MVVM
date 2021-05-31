package com.arbaelbarca.dagger2_with_retrofit_mvvm

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.arbaelbarca.dagger2_with_retrofit_mvvm.adapter.AdapterUsers
import com.arbaelbarca.dagger2_with_retrofit_mvvm.domain.response.ItemsItem
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

        initial()
    }

    private fun initial() {
        viewmodelMain.getListUser()
        observerData()
    }

    private fun observerData() {
        viewmodelMain.observerUsers().observe(this, Observer {
            when (it) {
                is UiState.Loading -> {
                    showView(progressList)
                }
                is UiState.Success -> {
                    hideView(progressList)
                    val dataItem = it.data.items
                    if (dataItem?.isNotEmpty()!!) {
                        initAdapter(dataItem)
                    }

                }
                is UiState.Failure -> {
                    hideView(progressList)
                    Toast.makeText(this, "Error ${it.throwable.message}", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun initAdapter(dataItem: MutableList<ItemsItem?>) {

        val groupieAdapter = GroupAdapter<GroupieViewHolder>().apply {
            dataItem.forEach {
                it?.let { it1 -> AdapterUsers(it1, this@MainActivity) }?.let { it2 -> add(it2) }
            }
        }

        setRvAdapterVertikal(rvListUser, groupieAdapter)

    }
}