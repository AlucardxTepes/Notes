package com.alucard.notes.notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alucard.notes.models.Note
import toothpick.Toothpick
import javax.inject.Inject

class NoteViewModel : ViewModel(), NoteListViewContract {

    @Inject
    lateinit var model: NoteModel

    private val _noteLiveData: MutableLiveData<MutableList<Note>> = MutableLiveData()
    val noteLiveData: LiveData<MutableList<Note>> = _noteLiveData

    init {
        val scope = Toothpick.openScope(this)
        Toothpick.inject(this, scope)
        _noteLiveData.postValue(model.getFakeData())
    }

}