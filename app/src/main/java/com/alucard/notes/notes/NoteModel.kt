package com.alucard.notes.notes

import com.alucard.notes.models.Note
import javax.inject.Inject

class NoteModel @Inject constructor(){

    fun getFakeData(): MutableList<Note> = mutableListOf(
        Note("Note1"),
        Note("Note2"),
        Note("Note3")
    )

}