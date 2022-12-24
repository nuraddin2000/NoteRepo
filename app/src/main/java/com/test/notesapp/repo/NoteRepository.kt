package com.test.notesapp.repo

import com.test.notesapp.roomdb.Note
import com.test.notesapp.roomdb.NoteDao
import javax.inject.Inject

class NoteRepository @Inject constructor (
        private val noteDao : NoteDao) : NoteRepositoryInterface {

    override suspend fun insertNote(note: Note) {
        this.noteDao.insertNote(note)
    }

    override fun getNotes(): List<Note> {
        return noteDao.getNotes()
    }

}