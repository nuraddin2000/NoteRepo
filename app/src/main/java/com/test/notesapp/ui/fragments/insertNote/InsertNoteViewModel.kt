package com.test.notesapp.ui.fragments.insertNote

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.notesapp.repo.NoteRepositoryInterface
import com.test.notesapp.roomdb.Note
import com.test.notesapp.util.Resource
import com.test.notesapp.util.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InsertNoteViewModel @Inject constructor(private val repository: NoteRepositoryInterface) :
    ViewModel() {

    private var insertNoteMsg = MutableLiveData<Resource<Note>>()

    fun getInsertNoteMsg() : MutableLiveData<Resource<Note>> {
        return insertNoteMsg
    }

    fun insertNote(note: Note) = CoroutineScope(Dispatchers.IO).launch {
        try {
            repository.insertNote(note)
            insertNoteMsg.postValue(Resource.success(note))
        }catch (e: Exception) {
            e.printStackTrace()
            insertNoteMsg.postValue(Resource.error("Something went wrong",data = null))
        }

    }
}