package com.ankita.newsapplication.database.data

import com.ankita.newsapplication.database.data.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)