package com.alucard.notes.tasks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alucard.notes.models.Task
import com.alucard.notes.models.Todo

class TaskViewModel : ViewModel() {

    val _taskLiveData: MutableLiveData<MutableList<Task>> = MutableLiveData()
    val taskLiveData: LiveData<MutableList<Task>> = _taskLiveData

    init {
        _taskLiveData.postValue(getFakeData())
    }

    fun getFakeData(): MutableList<Task> = mutableListOf(
        Task("Task1", mutableListOf(
            Todo("Todo1", true),
            Todo("Todo2")
        )),
        Task("Task2")
    )

}