package com.vunh.android.demoahamove.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vunh.android.demoahamove.R
import com.vunh.android.demoahamove.domain.entity.PopularEntity

class PopularAdapter : ListAdapter<PopularEntity, PopularAdapter.ViewHolder>(Callback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var binding = LayoutInflater.from(parent.context).inflate(R.layout.item_popular, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val tvName: TextView = itemView.findViewById(R.id.tv_name)
        private val tvDescription: TextView = itemView.findViewById(R.id.tv_description)
        private val tvLanguage: TextView = itemView.findViewById(R.id.tv_language)
        private val tvStar: TextView = itemView.findViewById(R.id.tv_star)
        private val tvFork: TextView = itemView.findViewById(R.id.tv_fork)
        private val tvVisibility: TextView = itemView.findViewById(R.id.tv_visibility)
        private val lineLanguage: LinearLayout = itemView.findViewById(R.id.line_language)
        private val imgLanguage: ImageView = itemView.findViewById(R.id.img_language)

        fun bind(item: PopularEntity) {
            tvName.text = item.name
            tvDescription.text = item.description
            tvLanguage.text = item.language
            tvStar.text = item.stargazersCount.toString()
            tvFork.text = item.forksCount.toString()
            tvVisibility.text = item.visibility
            lineLanguage.visibility = View.GONE.takeIf { item.visibility?.isEmpty() == true } ?: View.VISIBLE
//            when(item.visibility.uppercase()) {
//                "JAVASCRIPT" -> imgLanguage.backgroundTintMode =
//            }
        }
    }
    override fun submitList(list: MutableList<PopularEntity>?) {
        super.submitList(ArrayList(list ?: arrayListOf()))
    }

    class Callback : DiffUtil.ItemCallback<PopularEntity>() {
        override fun areItemsTheSame(oldItem: PopularEntity, newItem: PopularEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PopularEntity, newItem: PopularEntity): Boolean {
            return oldItem == newItem
        }

    }
}