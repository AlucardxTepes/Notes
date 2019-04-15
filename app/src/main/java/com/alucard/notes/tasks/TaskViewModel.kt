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
        loadData()
    }

    fun loadData() {
        model.retrieveTasks { nullableList ->
            nullableList?.let {
                _taskLiveData.postValue(it.toMutableList())
            }
        }
    }

    override fun onTodoUpdated(taskIndex: Int, todoIndex: Int, isComplete: Boolean) {
        _taskLiveData.value?.let {
            val todo = it[taskIndex].todos[todoIndex]
            todo.apply {
                this.isComplete = isComplete
                this.taskId = taskId
            }
            model.updateTodo(todo) {
                loadData() // refresh list
            }
        }
    }

    override fun onTaskDeleted(taskIndex: Int) {
        _taskLiveData.value?.let {
            model.deleteTask(it[taskIndex]) {
                loadData() // refresh view
            }
        }
    }
}