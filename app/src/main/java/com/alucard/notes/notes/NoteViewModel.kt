package com.alucard.notes.notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alucard.notes.models.Note

class NoteViewModel : ViewModel(), NoteListViewContract {

    private val model: NoteModel = NoteModel()
    private val _noteLiveData: MutableLiveData<MutableList<Note>> = MutableLiveData()
    val noteLiveData: LiveData<MutableList<Note>> = _noteLiveData

    init {
        _noteLiveData.postValue(model.getFakeData())
    }

}