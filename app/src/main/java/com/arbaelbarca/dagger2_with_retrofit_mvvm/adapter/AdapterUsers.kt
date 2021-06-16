package com.arbaelbarca.dagger2_with_retrofit_mvvm.adapter

import com.arbaelbarca.dagger2_with_retrofit_mvvm.R
import com.arbaelbarca.dagger2_with_retrofit_mvvm.domain.response.ItemsItem
import com.arbaelbarca.dagger2_with_retrofit_mvvm.presentation.listener.OnClickItem
import com.arbaelbarca.dagger2_with_retrofit_mvvm.utils.loadImageUrl
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.layout_item_users.view.*

class AdapterUsers(val itemsItem: ItemsItem, val onClickItem: OnClickItem) : Item() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.apply {
            itemView.apply {
                imgUser.loadImageUrl(itemsItem.avatarUrl.toString(), context)
                tvNameUser.text = itemsItem.login
            }

            itemView.setOnClickListener {
                onClickItem.ClickItem(position, itemsItem)
            }
        }
    }


    override fun getLayout(): Int = R.layout.layout_item_users
}