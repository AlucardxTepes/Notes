package com.alucard.notes.tasks

import com.alucard.notes.models.Task
import com.alucard.notes.models.Todo

class TaskModel {

    fun getFakeData(): MutableList<Task> = mutableListOf(
        Task("Task1", mutableListOf(
            Todo("Todo1", true),
            Todo("Todo2")
        )),
        Task("Task2")
    )

}