package com.alucard.notes.tasks

import com.alucard.notes.models.Task
import com.alucard.notes.models.Todo

typealias SuccessCallback = (Boolean) ->Unit

interface ITaskModel {

    fun addTask(task: Task, callback: SuccessCallback)
    fun updateTask(task: Task, callback: SuccessCallback)
    fun updateTodo(todo: Todo, callback: SuccessCallback)
    fun deleteTask(task: Task, callback: SuccessCallback)
    fun retrieveTasks(callback: (List<Task>?) -> Unit)
}
