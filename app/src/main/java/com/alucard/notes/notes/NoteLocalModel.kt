package com.alucard.notes.notes

import com.alucard.notes.models.Note
import javax.inject.Inject

class NoteLocalModel @Inject constructor() : INoteModel{

    override fun getFakeData(): MutableList<Note> = mutableListOf(
        Note("Note1"),
        Note("Note2"),
        Note("Note3")
    )

    override fun addNote(note: Note, callback: SuccessCallback) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateNote(note: Note, callback: SuccessCallback) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteNote(note: Note, callback: SuccessCallback) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun retrieveNotes(): List<Note> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}