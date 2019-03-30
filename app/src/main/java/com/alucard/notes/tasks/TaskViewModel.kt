package com.alucard.notes.tasks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alucard.notes.models.Task

class TaskViewModel : ViewModel(), TaskListViewContract {

    private val model: TaskModel = TaskModel()
    val _taskLiveData: MutableLiveData<MutableList<Task>> = MutableLiveData()
    val taskLiveData: LiveData<MutableList<Task>> = _taskLiveData

    init {
        _taskLiveData.postValue(model.getFakeData())
    }

    override fun onTodoUpdated(taskIndex: Int, todoIndex: Int, isComplete: Boolean) {
        _taskLiveData.value?.get(taskIndex)?.todos?.get(todoIndex)?.isComplete = isComplete
    }

}