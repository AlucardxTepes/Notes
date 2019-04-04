package com.alucard.notes.tasks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alucard.notes.models.Task
import toothpick.Toothpick
import javax.inject.Inject

class TaskViewModel : ViewModel(), TaskListViewContract {

    @Inject
    lateinit var model: TaskModel

    private val _taskLiveData: MutableLiveData<MutableList<Task>> = MutableLiveData()
    val taskLiveData: LiveData<MutableList<Task>> = _taskLiveData

    init {
        val scope = Toothpick.openScope(this)
        Toothpick.inject(this, scope)
        _taskLiveData.postValue(model.getFakeData())
    }

    override fun onTodoUpdated(taskIndex: Int, todoIndex: Int, isComplete: Boolean) {
        _taskLiveData.value?.get(taskIndex)?.todos?.get(todoIndex)?.isComplete = isComplete
    }

}