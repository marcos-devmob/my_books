package com.marcosalves.mybooks.repository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.marcosalves.mybooks.entity.BookEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDAO {

    /*
    Entity
    ListEntity
    LiveData<Entity>
    Flow<Entity>
    Cursor
    Int, Long, Boolean
    * */

    @Query("SELECT * FROM Book")
    fun getAllBooks(): Flow<List<BookEntity>>

    @Query("SELECT * FROM Book WHERE favorite = 1")
    fun getFavoriteBooks(): Flow<List<BookEntity>>

    @Query("SELECT * FROM Book WHERE id = :id")
    suspend fun getBookById(id: Int): BookEntity

    @Update
    suspend fun update(book: BookEntity)

    @Delete
    suspend fun delete(book: BookEntity): Int

    @Insert
    suspend fun insert(book: List<BookEntity>)

}

