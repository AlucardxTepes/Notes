package com.alucard.notes.notes

import com.alucard.notes.application.NoteApplication
import com.alucard.notes.database.RoomDatabaseClient
import com.alucard.notes.models.Note
import kotlinx.coroutines.*
import javax.inject.Inject

const val TIMEOUT_DURATION_MILLIS = 3000L

class NoteLocalModel @Inject constructor() : INoteModel {

    private val databaseClient = RoomDatabaseClient.getInstance(NoteApplication.instance.applicationContext)

    private fun performOperationWithTimeout(function: () -> Unit, callback: SuccessCallback) {
        GlobalScope.launch {
            val job = async {
                try {
                    withTimeout(TIMEOUT_DURATION_MILLIS) {
                        function.invoke()
                    }
                } catch (e: Exception) {
                    callback.invoke(false)
                }
            }
            job.await()
            callback.invoke(true)
        }
    }

    override fun addNote(note: Note, callback: SuccessCallback) {
        performOperationWithTimeout({ databaseClient.noteDAO().addNote(note) }, callback)
    }

    override fun updateNote(note: Note, callback: SuccessCallback) {
        performOperationWithTimeout({ databaseClient.noteDAO().updateNote(note) }, callback)
    }

    override fun deleteNote(note: Note, callback: SuccessCallback) {
        performOperationWithTimeout({ databaseClient.noteDAO().deleteNote(note) }, callback)
    }

    override fun retrieveNotes(callback: (List<Note>?) -> Unit) {
        GlobalScope.launch {
            val job = async {
                withTimeoutOrNull(TIMEOUT_DURATION_MILLIS) { databaseClient.noteDAO().retrieveNotes() }
            }
            callback.invoke(job.await())
        }
    }


}