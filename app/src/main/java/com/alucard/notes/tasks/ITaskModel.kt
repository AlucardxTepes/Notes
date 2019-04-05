package com.alucard.notes.tasks

import com.alucard.notes.models.Task

typealias SuccessCallback = (Boolean) ->Unit

interface ITaskModel {

    fun addTask(task: Task, callback: SuccessCallback)
    fun updateTask(task: Task)
    fun deleteTask(task: Task)
    fun retrieveTasks(): List<Task>


    fun getFakeData(): MutableList<Task>
}
