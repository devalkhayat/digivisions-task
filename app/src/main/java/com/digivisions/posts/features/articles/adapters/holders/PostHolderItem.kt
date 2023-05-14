package com.digivisions.posts.features.articles.adapters.holders

import android.content.Context
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.digivisions.domain.articles.models.Article
import com.digivisions.posts.databinding.ItemPostBinding
import com.digivisions.posts.util.eventListners.RecycleViewEventListener


class PostHolderItem(
    val binding: ItemPostBinding,
    val clickEventListener: RecycleViewEventListener
):RecyclerView.ViewHolder(binding.root) {

    fun bind(data: Article){
        binding.apply {
            tvTitle.text=data.title
            root.setOnClickListener {
                clickEventListener.onClick(data)
            }
        }

    }

}