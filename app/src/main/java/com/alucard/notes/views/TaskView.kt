package com.alucard.notes.views

import android.content.Context
import android.graphics.Paint
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.TextView
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
                .apply {
                    initView(todo) {
                        if (isTaskComplete()) {
                            createStrikeThrough(titleView)
                        } else {
                            removeStrikeThrough(titleView)
                        }
                    }
                }
            todoContainer.addView(viewTodo)
        }
    }

    private fun isTaskComplete(): Boolean = task.todos.none { !it.isComplete }

    private fun createStrikeThrough(textView: TextView) {
        textView.paintFlags = textView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
    }

    private fun removeStrikeThrough(textView: TextView) {
        textView.paintFlags = textView.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
    }
}