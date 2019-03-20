package com.alucard.notes.tasks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alucard.notes.R
import com.alucard.notes.foundations.BaseRecyclerAdapter
import com.alucard.notes.models.Task
import com.alucard.notes.views.TodoView
import kotlinx.android.synthetic.main.item_task.view.*

class TaskAdapter(
    taskList: MutableList<Task> = mutableListOf()
) : BaseRecyclerAdapter<Task>(taskList) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false))

    class ViewHolder(view: View) : BaseViewHolder<Task>(view) {
        override fun onBind(modelEntity: Task) {
            view.titleView.text = modelEntity.title

            modelEntity.todos.forEach { todo ->
                val todoView = (LayoutInflater.from(view.context).inflate(R.layout.view_todo, view.todoContainer, false) as TodoView)
                    .apply {
                        initView(todo)
                    }
                view.todoContainer.addView(todoView)
            }
        }
    }

}