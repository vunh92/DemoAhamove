package com.vunh.android.demoahamove.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.vunh.android.demoahamove.databinding.ItemPopularBinding
import com.vunh.android.demoahamove.domain.entity.PopularEntity

class PopularPagingAdapter(
    private val popularClickListener: (PopularEntity?) -> Unit
) : PagingDataAdapter<PopularEntity, PopularPagingAdapter.ViewHolder>(PopularDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.itemView.setOnClickListener {
            popularClickListener.invoke(item)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemPopularBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    class ViewHolder(private val binding: ItemPopularBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PopularEntity?) {
            if(item != null) {
                binding.tvName.text = item.name
                binding.tvDescription.text = item.description
                binding.tvLanguage.text = item.language
                binding.tvStar.text = item.stargazersCount.toString()
                binding.tvFork.text = item.forksCount.toString()
                binding.tvVisibility.text = item.visibility
                binding.lineLanguage.visibility = View.GONE.takeIf { item.visibility?.isEmpty() == true } ?: View.VISIBLE
            }
        }
    }

    class PopularDiffCallback : DiffUtil.ItemCallback<PopularEntity>() {
        override fun areItemsTheSame(oldItem: PopularEntity, newItem: PopularEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PopularEntity, newItem: PopularEntity): Boolean {
            return oldItem == newItem
        }

    }
}