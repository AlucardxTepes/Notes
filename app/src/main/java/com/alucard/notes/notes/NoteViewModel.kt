package com.alucard.notes.notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alucard.notes.foundations.ApplicationScope
import com.alucard.notes.models.Note
import toothpick.Toothpick
import javax.inject.Inject

class NoteViewModel : ViewModel(), NoteListViewContract {

    @Inject
    lateinit var model: INoteModel

    private val _noteLiveData: MutableLiveData<List<Note>> = MutableLiveData()
    val noteLiveData: LiveData<List<Note>> = _noteLiveData

    init {
        Toothpick.inject(this, ApplicationScope.scope)
        loadData()
    }

    fun loadData() {
        _noteLiveData.postValue(model.retrieveNotes())
    }

    override fun onDeleteNote(note: Note) {
        model.deleteNote(note) {
            if (it) {
                // trigger a data reload
                loadData()
            }
        }
    }
}