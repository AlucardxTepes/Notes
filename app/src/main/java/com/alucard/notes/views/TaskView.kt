package com.alucard.notes.views

import android.content.Context
import android.graphics.Paint
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

    fun initView(task: Task, todoCheckedCallback: (Int, Boolean) -> Unit) {
        this.task = task
        titleView.text = task.title
        task.todos.forEachIndexed { todoIndex: Int, todo: Todo ->
            val viewTodo =
                (LayoutInflater.from(context).inflate(R.layout.view_todo, todoContainer, false) as TodoView).apply {
                    initView(todo) { isChecked ->
                        todoCheckedCallback.invoke(todoIndex, isChecked)

                        if (isTaskComplete()) {
                            createStrikeThrough()
                        } else {
                            removeStrikeThrough()
                        }
                    }
                }
            todoContainer.addView(viewTodo)
        }
    }

    private fun isTaskComplete(): Boolean = task.todos.none { !it.isComplete }

    private fun createStrikeThrough() {
        titleView.paintFlags = titleView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
    }

    private fun removeStrikeThrough() {
        titleView.paintFlags = titleView.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
    }
}