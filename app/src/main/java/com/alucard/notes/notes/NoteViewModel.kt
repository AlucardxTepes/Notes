package com.alucard.notes.notes

import androidx.lifecycle.ViewModel
import com.alucard.notes.models.Note

class NoteViewModel : ViewModel() {

    fun getFakeData(): MutableList<Note> = mutableListOf(
        Note("Note1"),
        Note("Note2"),
        Note("Note3")
    )

}