package com.test.notesapp.roomdb

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArt(art : Note)

    @Delete
    suspend fun deleteArt(art: Note)

    @Query("SELECT * FROM notes")
    fun observeArts(): List<Note>

}