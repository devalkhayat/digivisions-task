package com.digivisions.posts.features.articles.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.digivisions.domain.articles.models.Article
import com.digivisions.posts.databinding.ItemPostBinding
import com.digivisions.posts.features.articles.adapters.holders.PostHolderItem
import com.digivisions.posts.util.eventListners.RecycleViewEventListener

class ArticlesAdapter( val clickEventListener: RecycleViewEventListener):PagingDataAdapter<Article, PostHolderItem>(DIFF_UTIL) {


    override fun onBindViewHolder(holder: PostHolderItem, position: Int) {
        holder.bind(getItem(position)!!)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolderItem {
        val itemBinding = ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostHolderItem(itemBinding,clickEventListener)
    }

    companion object {
        val DIFF_UTIL = object : DiffUtil.ItemCallback<Article>() {
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.title == newItem.title
            }
        }
    }
}