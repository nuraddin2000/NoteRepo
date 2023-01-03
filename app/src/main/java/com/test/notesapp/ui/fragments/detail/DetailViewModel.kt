package com.test.notesapp.ui.fragments.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.notesapp.repo.NoteRepositoryInterface
import com.test.notesapp.roomdb.Note
import com.test.notesapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val repository: NoteRepositoryInterface): ViewModel() {

    private var noteResult = MutableLiveData<Resource<Note>>()

    fun getNoteResult() : MutableLiveData<Resource<Note>> {
        return noteResult
    }

    fun getNoteById(noteId:Int) = CoroutineScope(Dispatchers.IO).launch {
        try {
            val result = repository.getNoteById(noteId)
            noteResult.postValue(Resource.success(result))
        }catch (e: Exception) {
            e.printStackTrace()
            noteResult.postValue(Resource.error("Something went wrong",data = null))
        }

    }
}