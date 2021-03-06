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

    fun initView(task: Task, todoCheckedCallback: (Int, Boolean) -> Unit, deleteCallback: () -> Unit) {
        resetChildViews()
        this.task = task
        initTaskTaskLine(deleteCallback)
        addChildViews(todoCheckedCallback)
    }

    private fun addChildViews(todoCheckedCallback: (Int, Boolean) -> Unit) {
        task.todos.forEachIndexed { todoIndex: Int, todo: Todo ->
            val viewTodo =
                (LayoutInflater.from(context).inflate(R.layout.view_todo, todoContainer, false) as TodoView).apply {
                    initView(todo) { isChecked ->
                        todoCheckedCallback.invoke(todoIndex, isChecked)

                        if (isTaskComplete()) {
                            this@TaskView.titleView.setStrikeThrough()
                        } else {
                            this@TaskView.titleView.removeStrikeThrough()
                        }
                    }
                }
            todoContainer.addView(viewTodo)
        }
    }

    private fun resetChildViews() {
        todoContainer.removeAllViewsInLayout()
    }

    private fun initTaskTaskLine(deleteCallback: () -> Unit) {
        titleView.text = task.title
        imageButton.setOnClickListener { deleteCallback.invoke() }
    }

    private fun isTaskComplete(): Boolean = task.todos.none { !it.isComplete }
}