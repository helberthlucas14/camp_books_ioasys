package com.example.books.data_local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.books.data_local.model.BookDataLocal

@Dao
interface BookDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addBooks(bookList: List<BookDataLocal>)


    @Query("SELECT * FROM book")
    fun getBooks(): List<BookDataLocal>
}