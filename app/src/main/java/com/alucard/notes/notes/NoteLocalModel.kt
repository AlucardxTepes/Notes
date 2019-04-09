package com.alucard.notes.notes

import com.alucard.notes.application.NoteApplication
import com.alucard.notes.database.RoomDatabaseClient
import com.alucard.notes.models.Note
import javax.inject.Inject

class NoteLocalModel @Inject constructor() : INoteModel{

    private val databaseClient = RoomDatabaseClient.getInstance(NoteApplication.instance.applicationContext)

    override fun getFakeData(): MutableList<Note> = databaseClient.noteDAO().retrieveNotes().toMutableList()
//        mutableListOf(
//        Note(description = "Note1"),
//        Note(description = "Note2"),
//        Note(description = "Note3")
//    )

    override fun addNote(note: Note, callback: SuccessCallback) {
        databaseClient.noteDAO().addNote(note)
        callback.invoke(true)
    }

    override fun updateNote(note: Note, callback: SuccessCallback) {
        databaseClient.noteDAO().updateNote(note)
    }

    override fun deleteNote(note: Note, callback: SuccessCallback) {
        databaseClient.noteDAO().deleteNote(note)
    }

    override fun retrieveNotes(): List<Note> = databaseClient.noteDAO().retrieveNotes()


}