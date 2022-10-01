package com.gauravbora.indiagbnews

import android.content.Context
import android.content.Intent
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NewsAdapter(val context: Context, val articles: List<Article>) :
    RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {

    class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var newsImage: ImageView = itemView.findViewById<ImageView>(R.id.newsImage)
        var newsTitle: TextView = itemView.findViewById<TextView>(R.id.newsTitle)
        var newsDescription: TextView = itemView.findViewById<TextView>(R.id.newsDescription)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.news_layout, parent, false)
        return ArticleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = articles[position]
        holder.newsTitle.text = article.title
        holder.newsDescription.text = article.description

//        image loading
        Glide.with(context).load(article.urlToImage).into(holder.newsImage)


        holder.itemView.setOnClickListener {
         val intent=Intent(context,NewsDetailsActivity::class.java)
            intent.putExtra("URL",article.url)
            context.startActivity(intent)
        }


    }

    override fun getItemCount(): Int {
        return articles.size
    }
}