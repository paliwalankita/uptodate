package com.ankita.newsapplication.repositoty

import com.ankita.newsapplication.api.RetrofitInstance
import com.ankita.newsapplication.database.NewsDatabase
import com.ankita.newsapplication.database.data.Article

class NewsRepository(val db: NewsDatabase) {

    suspend fun getCurrentNews(countryCode: String, pageNumber: Int) =
        RetrofitInstance.api.getCurrentNews(countryCode, pageNumber)

    suspend fun searchNews(searchQuery: String, pageNumber: Int) =
        RetrofitInstance.api.getSearchNews(searchQuery, pageNumber)

    suspend fun upsert(article: Article) = db.getNewsDao().insert(article)

    fun getSavedArticles() = db.getNewsDao().getAllArticles()

    suspend fun deleteArticle(article: Article) = db.getNewsDao().deleteArticle(article)

}