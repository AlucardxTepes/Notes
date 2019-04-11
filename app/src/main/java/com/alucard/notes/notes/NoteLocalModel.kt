package com.alucard.notes.notes

import com.alucard.notes.application.NoteApplication
import com.alucard.notes.database.RoomDatabaseClient
import com.alucard.notes.models.Note
import javax.inject.Inject

class NoteLocalModel @Inject constructor() : INoteModel{

    private val databaseClient = RoomDatabaseClient.getInstance(NoteApplication.instance.applicationContext)

    override fun getFakeData(): MutableList<Note> = databaseClient.noteDAO().retrieveNotes()

    override fun addNote(note: Note, callback: SuccessCallback) {
        databaseClient.noteDAO().addNote(note)
        callback.invoke(true)
    }

    override fun updateNote(note: Note, callback: SuccessCallback) {
        databaseClient.noteDAO().updateNote(note)
        callback.invoke(true)
    }

    override fun deleteNote(note: Note, callback: SuccessCallback) {
        databaseClient.noteDAO().deleteNote(note)
        callback.invoke(true)
    }

    override fun retrieveNotes(): MutableList<Note> = databaseClient.noteDAO().retrieveNotes()


}