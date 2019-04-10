package com.alucard.notes.notes

import com.alucard.notes.models.Note

typealias SuccessCallback = (Boolean) -> Unit

interface INoteModel {

    fun addNote(note: Note, callback: SuccessCallback)
    fun updateNote(note: Note, callback: SuccessCallback)
    fun deleteNote(note: Note, callback: SuccessCallback)
    fun retrieveNotes(): MutableList<Note>

    fun getFakeData(): MutableList<Note>
}