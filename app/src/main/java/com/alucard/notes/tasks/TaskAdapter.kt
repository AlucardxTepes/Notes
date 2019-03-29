package com.alucard.notes.tasks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alucard.notes.R
import com.alucard.notes.foundations.BaseRecyclerAdapter
import com.alucard.notes.models.Task
import com.alucard.notes.navigation.NavigationActivity
import com.alucard.notes.views.TaskView
import kotlinx.android.synthetic.main.view_add_button.view.*

class TaskAdapter(
    taskList: MutableList<Task> = mutableListOf(),
    val touchActionDelegate: TasksListFragment.TouchActionDelegate,
    val dataActionDelegate: TaskListViewContract
) : BaseRecyclerAdapter<Task>(taskList) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        if (viewType == TYPE_INFO) {
            TaskViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false))
        } else {
            AddButtonViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_add_button, parent, false))
        }

    inner class TaskViewHolder(view: View) : BaseViewHolder<Task>(view) {
        override fun onBind(modelEntity: Task, listIndex: Int) {
            (view as TaskView).initView(modelEntity) { todoIndex: Int, isChecked: Boolean ->
                dataActionDelegate.onTodoUpdated(listIndex, todoIndex, isChecked)
            }
        }
    }

    inner class AddButtonViewHolder(view: View): BaseRecyclerAdapter.AddButtonViewHolder(view) {
        override fun onBind(modelEntity: Unit, listIndex: Int) {
            view.buttonText.text = view.context.getString(R.string.add_button_task)
            view.setOnClickListener {
                touchActionDelegate.onAddButtonClicked(NavigationActivity.FRAGMENT_VALUE_TASK)
            }
        }
    }

}