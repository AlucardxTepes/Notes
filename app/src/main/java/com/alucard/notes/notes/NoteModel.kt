package com.alucard.notes.notes

import com.alucard.notes.models.Note

class NoteModel {

    fun getFakeData(): MutableList<Note> = mutableListOf(
        Note("Note1"),
        Note("Note2"),
        Note("Note3")
    )

}