package com.test.notesapp.repo

import androidx.lifecycle.LiveData
import com.test.notesapp.roomdb.Note
import com.test.notesapp.roomdb.NoteDao
import javax.inject.Inject

class NoteRepository @Inject constructor (
        private val noteDao : NoteDao) : NoteRepositoryInterface {

    override suspend fun insertNote(note: Note) {
        this.noteDao.insertArt(note)
    }

    override fun getNotes(): List<Note> {
        return noteDao.observeArts()
    }

}