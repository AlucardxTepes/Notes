package com.alucard.notes.notes

import com.alucard.notes.models.Note

interface NoteListViewContract {

    fun onDeleteNote(note: Note)

}