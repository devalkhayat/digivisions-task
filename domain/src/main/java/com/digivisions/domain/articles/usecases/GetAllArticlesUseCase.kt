package com.digivisions.domain.articles.usecases

import com.digivisions.domain.articles.repositories.ArticlesRepo


class GetAllArticlesUseCase(private val articlesRepo: ArticlesRepo) {
    suspend operator fun invoke()=articlesRepo.getAll()
}
