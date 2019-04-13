package com.alucard.notes.tasks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alucard.notes.models.Task
import toothpick.Toothpick
import toothpick.config.Module
import javax.inject.Inject

class TaskViewModel : ViewModel(), TaskListViewContract {

    @Inject
    lateinit var model: ITaskModel

    private val _taskLiveData: MutableLiveData<MutableList<Task>> = MutableLiveData()
    val taskLiveData: LiveData<MutableList<Task>> = _taskLiveData

    init {
        val scope = Toothpick.openScope(this)
        scope.installModules(Module().apply {
            bind(ITaskModel::class.java).toInstance(TaskLocalModel())
        })
        Toothpick.inject(this, scope)
        _taskLiveData.postValue(model.getFakeData())
    }

    fun loadData() {
        _taskLiveData.postValue(model.retrieveTasks().toMutableList())
    }

    override fun onTodoUpdated(taskIndex: Int, todoIndex: Int, isComplete: Boolean) {
        _taskLiveData.value?.get(taskIndex)?.todos?.get(todoIndex)?.isComplete = isComplete
    }

}