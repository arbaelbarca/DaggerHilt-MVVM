package com.arbaelbarca.dagger2_with_retrofit_mvvm.presentation.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.arbaelbarca.dagger2_with_retrofit_mvvm.R
import com.arbaelbarca.dagger2_with_retrofit_mvvm.datasource.local.db.room.entity.EntityUser
import com.arbaelbarca.dagger2_with_retrofit_mvvm.domain.response.ItemsItem
import com.arbaelbarca.dagger2_with_retrofit_mvvm.utils.UiState
import com.arbaelbarca.dagger2_with_retrofit_mvvm.utils.loadImageUrl
import com.arbaelbarca.dagger2_with_retrofit_mvvm.viewmodel.ViewModelMain
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_detail_users.*

@AndroidEntryPoint
class DetailUsersActivity : AppCompatActivity() {
    var itemsItem: ItemsItem? = null
    val viewmodelMain: ViewModelMain by viewModels()
    var entityUser: EntityUser? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_users)

        initial()
    }

    private fun initial() {
        observeAddFav()
        observeDeleteFav()
        observeCheckFav()
        observeIsCheckFav()

        itemsItem = intent.getParcelableExtra("data")
        viewmodelMain.ischeckFavUser(itemsItem?.login.toString())

        imgUserDetail.loadImageUrl(itemsItem?.avatarUrl.toString(), this)
        tvNameUserDetail.text = itemsItem?.login

        floatFav.setOnClickListener {
            entityUser = EntityUser(
                itemsItem?.id!!,
                itemsItem?.login.toString(),
                itemsItem?.avatarUrl.toString()
            )
            viewmodelMain.checkFavUser(entityUser!!.username)
        }
    }

    private fun observeIsCheckFav() {
        viewmodelMain.observeIsCheckFav()
            .observe(this, Observer {
                when (it) {
                    is UiState.Loading -> {
                    }
                    is UiState.Success -> {
                        val listEntityUsers = it.data
                        if (listEntityUsers.isNotEmpty()) {
                            listEntityUsers.forEach {
                                val idUser = it.id
                                if (idUser == entityUser?.id) {
                                    floatFav.setImageResource(R.drawable.ic_baseline_star_border_24)
                                } else floatFav.setImageResource(R.drawable.ic_baseline_star_24)
                            }
                        }
                    }
                    is UiState.Failure -> {
                        Toast.makeText(this, "Error ${it.throwable.message}", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            })
    }

    private fun observeCheckFav() {
        viewmodelMain.observeCheckFav()
            .observe(this, Observer {
                when (it) {
                    is UiState.Loading -> {
                    }
                    is UiState.Success -> {
                        val listEntityUsers = it.data
                        if (listEntityUsers.isNotEmpty()) {
                            listEntityUsers.forEach {
                                val idUser = it.id
                                if (idUser == entityUser?.id) {
                                    entityUser?.let { it1 -> viewmodelMain.deleteFavUser(it1) }
                                } else entityUser?.let { it1 -> viewmodelMain.addFavUser(it1) }
                            }
                        } else entityUser?.let { it1 -> viewmodelMain.addFavUser(it1) }
                    }
                    is UiState.Failure -> {
                        Toast.makeText(this, "Error ${it.throwable.message}", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            })
    }

    private fun observeDeleteFav() {
        viewmodelMain.observeDeleteFav()
            .observe(this, Observer {
                when (it) {
                    is UiState.Loading -> {
                    }
                    is UiState.Success -> {
                        val dataItem = it.data
                        Toast.makeText(this, dataItem, Toast.LENGTH_SHORT).show()
                        floatFav.setImageResource(R.drawable.ic_baseline_star_border_24)
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
                        floatFav.setImageResource(R.drawable.ic_baseline_star_24)
                    }
                    is UiState.Failure -> {
                        Toast.makeText(this, "Error ${it.throwable.message}", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            })
    }

}