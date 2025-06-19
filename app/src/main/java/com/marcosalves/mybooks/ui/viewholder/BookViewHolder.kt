package com.marcosalves.mybooks.ui.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.marcosalves.mybooks.R
import com.marcosalves.mybooks.databinding.ItemBookBinding
import com.marcosalves.mybooks.entity.BookEntity
import com.marcosalves.mybooks.ui.listener.BookListener

class BookViewHolder(private val item: ItemBookBinding, private val listener: BookListener) :
    RecyclerView.ViewHolder(item.root) {

    fun bind(book: BookEntity) {
        item.textviewTitle.text = book.title
        item.textviewAuthor.text = book.author
        item.textviewGenre.text = book.genre

        item.textviewTitle.setOnClickListener { listener.onClick(book.id) }
        item.imageviewFavorite.setOnClickListener { listener.onFavoriteClick(book.id) }

        setGenreBackground(book.genre)

        updateFavoriteIcon(book.favorite)
    }

    private fun updateFavoriteIcon(favorite: Boolean) {
        if (favorite) {
            item.imageviewFavorite.setImageResource(R.drawable.ic_favorite)
        } else {
            item.imageviewFavorite.setImageResource(R.drawable.ic_favorite_empty)
        }
    }

    private fun setGenreBackground(genre: String) {
        when (genre) {
            "Terror" -> {
                item.textviewGenre.setBackgroundResource(R.drawable.rounded_label_red)
            }

            "Fantasia" -> {
                item.textviewGenre.setBackgroundResource(R.drawable.rounded_label_fantasy)
            }

            else -> {
                item.textviewGenre.setBackgroundResource(R.drawable.rounded_label_teal)
            }
        }
    }

}