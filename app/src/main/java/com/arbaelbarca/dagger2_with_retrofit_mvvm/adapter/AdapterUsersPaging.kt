package com.arbaelbarca.dagger2_with_retrofit_mvvm.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.arbaelbarca.dagger2_with_retrofit_mvvm.databinding.LayoutItemUsersBinding
import com.arbaelbarca.dagger2_with_retrofit_mvvm.domain.response.ItemsItem
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class AdapterUsersPaging :
    PagingDataAdapter<ItemsItem, AdapterUsersPaging.ViewHolder>(UserComparator) {
    class ViewHolder(val itemUserBinding: LayoutItemUsersBinding) :
        RecyclerView.ViewHolder(itemUserBinding.root) {
        fun bind(itemsItem: ItemsItem) {
            itemUserBinding.apply {
                tvNameUser.text = itemsItem.login
                Glide.with(itemView)
                    .load(itemsItem.avatarUrl)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(imgUser)
            }
        }
    }

    object UserComparator : DiffUtil.ItemCallback<ItemsItem>() {
        override fun areItemsTheSame(oldItem: ItemsItem, newItem: ItemsItem) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: ItemsItem, newItem: ItemsItem) = oldItem == newItem
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val items = getItem(position)
        if (items != null)
            holder.bind(items)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding =
            LayoutItemUsersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }
}

