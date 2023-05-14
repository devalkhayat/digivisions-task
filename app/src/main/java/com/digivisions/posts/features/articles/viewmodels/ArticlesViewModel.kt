package com.digivisions.posts.features.articles.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.digivisions.domain.articles.models.Article
import com.digivisions.domain.articles.usecases.GetAllArticlesUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class ArticlesViewModel(private val getAllArticlesUseCase: GetAllArticlesUseCase): ViewModel() {

    private  val TAG = "ArticlesViewModel"
    private val articles= MutableLiveData<Flow<PagingData<Article>>>()
    val articlesResponseLive: LiveData<Flow<PagingData<Article>>>
        get() = articles


    fun getArticles() {
        viewModelScope.launch {
            articles.postValue(getAllArticlesUseCase()!!)
        }
    }
}