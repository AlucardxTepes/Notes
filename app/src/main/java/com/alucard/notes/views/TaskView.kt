package com.alucard.notes.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.alucard.notes.R
import com.alucard.notes.models.Task
import com.alucard.notes.models.Todo
import kotlinx.android.synthetic.main.item_task.view.*

class TaskView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private lateinit var task: Task

    fun initView(task: Task) {
        this.task = task
        titleView.text = task.title
        task.todos.forEach { todo: Todo ->
            val viewTodo = (LayoutInflater.from(context).inflate(R.layout.view_todo, todoContainer, false) as TodoView)
            viewTodo.initView(todo)
            if (isTaskComplete()) {
                viewTodo.createStrikeThrough(titleView)
            } else {
                viewTodo.removeStrikeThrough(titleView)
            }
            todoContainer.addView(viewTodo)
        }
    }

    private fun isTaskComplete(): Boolean = task.todos.none { !it.isComplete }
}