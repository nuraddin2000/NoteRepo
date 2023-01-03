package com.test.notesapp.roomdb

import androidx.room.*

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note : Note)

    @Query("SELECT * FROM notes")
    fun getNotes(): List<Note>

    @Query("SELECT * FROM notes WHERE id=:id ")
    fun getNoteById(id: Int): Note

}