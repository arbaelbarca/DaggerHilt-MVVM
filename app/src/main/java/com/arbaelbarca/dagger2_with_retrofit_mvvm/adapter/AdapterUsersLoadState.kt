package com.arbaelbarca.dagger2_with_retrofit_mvvm.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.arbaelbarca.dagger2_with_retrofit_mvvm.databinding.LayoutItemFooterPagingBinding

class AdapterUsersLoadState(val retry: () -> Unit) :
    LoadStateAdapter<AdapterUsersLoadState.StateViewHolder>() {
    inner class StateViewHolder(val layoutItemFooterPagingBinding: LayoutItemFooterPagingBinding) :
        RecyclerView.ViewHolder(layoutItemFooterPagingBinding.root) {

        init {
            layoutItemFooterPagingBinding.btnRetry.setOnClickListener {
                retry.invoke()
            }
        }

        fun bind(loadState: LoadState) {
            layoutItemFooterPagingBinding.apply {
                progressLoadPaging.isVisible = loadState is LoadState.Loading
                btnRetry.isVisible = loadState !is LoadState.Loading
            }
        }
    }

    override fun onBindViewHolder(holder: StateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): StateViewHolder {
        val binding = LayoutItemFooterPagingBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return StateViewHolder(binding)
    }
}