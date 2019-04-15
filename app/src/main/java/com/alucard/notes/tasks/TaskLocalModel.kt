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

    override fun addTask(task: Task, callback: SuccessCallback) {
        GlobalScope.launch {
            val masterJob = async {
                try {2
                    databaseClient.taskDAO().addTask(task)
                } catch (e: Exception) {
                    callback.invoke(false)
                }
                addTodosJob(task)
            }
            masterJob.await()
            callback.invoke(true)
        }
    }

    private fun addTodosJob(task: Task): Job = GlobalScope.async {
        task.todos.forEach { databaseClient.taskDAO().addTodo(it) }
    }

    override fun updateTask(task: Task, callback: SuccessCallback) {
        performOperationWithTimeout({ databaseClient.taskDAO().updateTask(task) }, callback)
    }

    override fun updateTodo(todo: Todo, callback: SuccessCallback) {
        performOperationWithTimeout({ databaseClient.taskDAO().updateTodo(todo) }, callback)
    }

    override fun deleteTask(task: Task, callback: SuccessCallback) {
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

    private fun addTodosInTask(task: Task) {
    }

}