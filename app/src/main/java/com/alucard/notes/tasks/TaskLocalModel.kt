package com.alucard.notes.tasks

import com.alucard.notes.models.Task
import com.alucard.notes.models.Todo
import javax.inject.Inject

class TaskLocalModel @Inject constructor() : ITaskModel {

    override fun getFakeData(): MutableList<Task> = mutableListOf(
        Task("Task1", mutableListOf(
            Todo("Todo1", true),
            Todo("Todo2")
        )),
        Task("Task2")
    )

    override fun addTask(task: Task, callback: SuccessCallback) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateTask(task: Task) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteTask(task: Task) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun retrieveTasks(): List<Task> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}