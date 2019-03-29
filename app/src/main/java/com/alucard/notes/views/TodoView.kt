package com.alucard.notes.views

import android.content.Context
import android.graphics.Paint
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.alucard.notes.models.Todo
import kotlinx.android.synthetic.main.view_todo.view.*

class TodoView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 1
) : ConstraintLayout(context, attrs, defStyleAttr) {

    fun initView(todo: Todo, callback: ((Boolean) -> Unit)? = null) {
        descriptionView.text = todo.description
        completeCheckBox.isChecked = todo.isComplete

        if (todo.isComplete) {
            createStrikeThrough()
        }

        setupCheckStateListener(todo, callback)
    }

    private fun setupCheckStateListener(todo: Todo, callback: ((Boolean) -> Unit)? = null) {
        completeCheckBox.setOnCheckedChangeListener { buttonView, isChecked ->
            todo.isComplete = isChecked
            callback?.invoke(isChecked)
            if (isChecked) {
                createStrikeThrough()
            } else {
                removeStrikeThrough()
            }
        }
    }

    fun createStrikeThrough() {
        descriptionView.paintFlags = descriptionView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
    }

    fun removeStrikeThrough() {
        descriptionView.paintFlags = descriptionView.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
    }
}
