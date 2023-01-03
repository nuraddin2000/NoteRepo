package com.test.notesapp.repo

import androidx.lifecycle.LiveData
import com.test.notesapp.roomdb.Note

interface NoteRepositoryInterface {

    suspend fun insertNote(note : Note)

    fun getNotes() : List<Note>

    fun getNoteById(id: Int) : Note

}