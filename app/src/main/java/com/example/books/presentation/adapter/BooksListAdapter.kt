package com.example.books.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.books.R
import com.example.books.domain.model.Book
import com.example.books.databinding.BookItemBinding

class BooksListAdapter(
    private val onBookClickListener: BookClickListener
) : ListAdapter<Book, BooksListAdapter.BookViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        return BookViewHolder.create(parent, onBookClickListener)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        return holder.bind(getItem(position))
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Book>() {
            override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean =
                oldItem == newItem
        }
    }

    class BookViewHolder(
        private val binding: BookItemBinding,
        private val onBookClickListener: BookClickListener
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(book: Book) {
            binding.run {

                tvBookTitle.text = book.name
                tvBookSubTitle.text = book.author
                tvBookPages.text = book.pages
                tvBookEditor.text = book.editor
                tvBookDate.text = book.date

                Glide
                    .with(itemView)
                    .load(book.imageUrl)
                    .placeholder(R.drawable.img_book)
                    .into(imgBook);

                itemView.setOnClickListener {
                    onBookClickListener.onBookClickListener(book)
                }
            }
        }

        companion object {
            fun create(
                parent: ViewGroup,
                onBookClickListener: BookClickListener
            ): BookViewHolder {
                val itemBinding = BookItemBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false)

                return BookViewHolder(itemBinding, onBookClickListener)
            }
        }
    }
}