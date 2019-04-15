package com.alucard.notes.tasks

import com.alucard.notes.application.NoteApplication
import com.alucard.notes.database.RoomDatabaseClient
import com.alucard.notes.models.Task
import com.alucard.notes.models.Todo
import kotlinx.coroutines.*
import javax.inject.Inject

const val TIMEOUT_DURATION_MILLIS = 3000L

class TaskLocalModel @Inject constructor() : ITaskModel {

    private val databaseClient = RoomDatabaseClient.getInstance(NoteApplication.instance.applicationContext)

    private suspend fun performOperationWithTimeout(function: () -> Unit, callback: SuccessCallback) {
        val job = GlobalScope.async {
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

    override suspend fun addTask(task: Task, callback: SuccessCallback) {
        val masterJob = GlobalScope.async {
            try {
                databaseClient.taskDAO().addTask(task)
            } catch (e: Exception) {
                callback.invoke(false)
            }
            addTodosJob(task)
        }
        masterJob.await()
        callback.invoke(true)
    }

    private fun addTodosJob(task: Task): Job = GlobalScope.async {
        task.todos.forEach { databaseClient.taskDAO().addTodo(it) }
    }

    override suspend fun updateTask(task: Task, callback: SuccessCallback) {
        performOperationWithTimeout({ databaseClient.taskDAO().updateTask(task) }, callback)
    }

    override suspend fun updateTodo(todo: Todo, callback: SuccessCallback) {
        performOperationWithTimeout({ databaseClient.taskDAO().updateTodo(todo) }, callback)
    }

    override suspend fun deleteTask(task: Task, callback: SuccessCallback) {
        performOperationWithTimeout({ databaseClient.taskDAO().deleteTask(task) }, callback)
    }

    override fun retrieveTasks(callback: (List<Task>?) -> Unit) {
        GlobalScope.launch {
            val job = async {
                withTimeoutOrNull(TIMEOUT_DURATION_MILLIS) {
                    databaseClient.taskDAO().retrieveTasks()
                }
            }
            callback.invoke(job.await())
        }
    }
}