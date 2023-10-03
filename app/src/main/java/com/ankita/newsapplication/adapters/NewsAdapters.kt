package com.ankita.newsapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ankita.newsapplication.database.NewsDatabase
import com.ankita.newsapplication.database.data.Article
import com.ankita.newsapplication.databinding.NewsListItemBinding
import com.bumptech.glide.Glide

class NewsAdapters : RecyclerView.Adapter<NewsAdapters.NewsViewHolder>() {

    inner class NewsViewHolder(val binding: NewsListItemBinding) : RecyclerView.ViewHolder(binding.root){

    }

    private val differCallBack = object: DiffUtil.ItemCallback<Article>(){
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAdapters.NewsViewHolder {
        return NewsViewHolder(
            NewsListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false)
        )
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article = differ.currentList[position]
        holder.binding.apply {
            Glide.with(ivArticleImage.context).load(article.urlToImage).into(ivArticleImage)
            tvSource.text = article.source.name
            tvTitle.text = article.title
            tvDescription.text = article.description
            tvPublishedAt.text = article.publishedAt
        }

        holder.binding.root.apply {
            setOnItemClickListener {
                onItemClickListener?.let {
                    it(article)
                }
            }
        }
    }

    private var onItemClickListener: ((Article) -> Unit)? = null

    fun setOnItemClickListener(listener: (Article) -> Unit){
        onItemClickListener = listener
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


}