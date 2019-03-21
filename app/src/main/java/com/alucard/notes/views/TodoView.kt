package com.alucard.notes.views

import android.content.Context
import android.graphics.Paint
import android.util.AttributeSet
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.alucard.notes.models.Todo
import kotlinx.android.synthetic.main.view_todo.view.*

class TodoView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 1
) : ConstraintLayout(context, attrs, defStyleAttr) {

    fun initView(todo: Todo) {
        completeCheckBox.isChecked = todo.isComplete
        descriptionView.text = todo.description

        if (todo.isComplete) {
            createStrikeThrough(descriptionView)
        }

        setupCheckStateListener()
    }

    private fun setupCheckStateListener() {
        completeCheckBox.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                createStrikeThrough(buttonView)
            } else {
                removeStrikeThrough(buttonView)
            }
        }
    }

    private fun createStrikeThrough(buttonView: TextView) {
        buttonView.paintFlags = buttonView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
    }

    private fun removeStrikeThrough(buttonView: TextView) {
        buttonView.paintFlags = buttonView.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
    }
}
