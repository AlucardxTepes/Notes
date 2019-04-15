package com.alucard.notes.tasks

import com.alucard.notes.models.Task
import com.alucard.notes.models.Todo

typealias SuccessCallback = (Boolean) ->Unit

interface ITaskModel {

    suspend fun addTask(task: Task, callback: SuccessCallback)
    suspend fun updateTask(task: Task, callback: SuccessCallback)
    suspend fun updateTodo(todo: Todo, callback: SuccessCallback)
    suspend fun deleteTask(task: Task, callback: SuccessCallback)
    fun retrieveTasks(callback: (List<Task>?) -> Unit)
}
