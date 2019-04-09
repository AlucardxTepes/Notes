package com.alucard.notes.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.alucard.notes.models.Note
import com.alucard.notes.models.Tag
import com.alucard.notes.models.TaskEntity
import com.alucard.notes.models.Todo

const val DATABASE_SCHEMA_VERSION = 1
const val DB_NAME = "local-db"

@Database(version = DATABASE_SCHEMA_VERSION, entities = [TaskEntity::class, Todo::class, Note::class, Tag::class])
abstract class RoomDatabaseClient : RoomDatabase() {

    // DAOs
    abstract fun noteDAO(): NoteDAO
    abstract fun taskDAO(): TaskDAO

    companion object {
        private var instance: RoomDatabaseClient? = null

        fun getInstance(context: Context): RoomDatabaseClient {
            if (instance == null) {
                instance = createDatabase(context)
            }
            return instance!!
        }

        private fun createDatabase(context: Context): RoomDatabaseClient =
            Room.databaseBuilder(context, RoomDatabaseClient::class.java, DB_NAME)
                .allowMainThreadQueries() // TODO: Replace with Kotlin Coroutines
                .build()
    }
}