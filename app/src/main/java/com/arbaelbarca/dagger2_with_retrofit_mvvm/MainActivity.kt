package com.arbaelbarca.dagger2_with_retrofit_mvvm

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.arbaelbarca.dagger2_with_retrofit_mvvm.adapter.AdapterUsers
import com.arbaelbarca.dagger2_with_retrofit_mvvm.datasource.local.db.room.entity.EntityUser
import com.arbaelbarca.dagger2_with_retrofit_mvvm.domain.response.ItemsItem
import com.arbaelbarca.dagger2_with_retrofit_mvvm.presentation.listener.OnClickItem
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

    val onClickItem = object : OnClickItem {
        override fun ClickItem(pos: Int, any: Any) {
            val dataItemUser = any as ItemsItem
            val entityUser = EntityUser(
                dataItemUser.id!!,
                dataItemUser.login.toString(),
                dataItemUser.avatarUrl.toString()
            )
            viewmodelMain.addFavUser(entityUser)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initial()
    }

    private fun initial() {
        viewmodelMain.getListUser()
        observerData()
        observeAddFav()
        observeDeleteFav()
    }

    private fun observeDeleteFav() {
        viewmodelMain.observeAddFav()
            .observe(this, Observer {
                when (it) {
                    is UiState.Loading -> {
                    }
                    is UiState.Success -> {
                        val dataItem = it.data
                        Toast.makeText(this, dataItem, Toast.LENGTH_SHORT).show()
                    }
                    is UiState.Failure -> {
                        Toast.makeText(this, "Error ${it.throwable.message}", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            })
    }

    private fun observeAddFav() {
        viewmodelMain.observeAddFav()
            .observe(this, Observer {
                when (it) {
                    is UiState.Loading -> {
                    }
                    is UiState.Success -> {
                        val dataItem = it.data
                        Toast.makeText(this, dataItem, Toast.LENGTH_SHORT).show()
                    }
                    is UiState.Failure -> {
                        Toast.makeText(this, "Error ${it.throwable.message}", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            })
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
                it?.let { it1 -> AdapterUsers(it1, onClickItem) }?.let { it2 -> add(it2) }
            }
        }

        setRvAdapterVertikal(rvListUser, groupieAdapter)

    }
}