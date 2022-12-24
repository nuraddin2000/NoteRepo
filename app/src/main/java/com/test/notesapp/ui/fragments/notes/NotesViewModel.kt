package com.test.notesapp.ui.fragments.notes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.notesapp.repo.NoteRepositoryInterface
import com.test.notesapp.roomdb.Note
import com.test.notesapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(private val repository: NoteRepositoryInterface): ViewModel() {

    private var notesResult = MutableLiveData<Resource<List<Note>>>()

    fun getNotesResult() : MutableLiveData<Resource<List<Note>>> {
        return notesResult
    }

    fun getNotes() = CoroutineScope(Dispatchers.IO).launch {
        try {
            val result = repository.getNotes()
            notesResult.postValue(Resource.success(result))
        }catch (e: Exception) {
            e.printStackTrace()
            notesResult.postValue(Resource.error("Something went wrong",data = null))
        }

    }
}