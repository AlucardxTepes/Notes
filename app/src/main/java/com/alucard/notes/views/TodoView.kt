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

    fun initView(todo: Todo, callback: (() -> Unit)? = null) {
        completeCheckBox.isChecked = todo.isComplete
        descriptionView.text = todo.description

        if (todo.isComplete) {
            createStrikeThrough(descriptionView)
        }

        setupCheckStateListener(callback)
    }

    private fun setupCheckStateListener(callback: (() -> Unit)? = null) {
        completeCheckBox.setOnCheckedChangeListener { buttonView, isChecked ->
            callback?.invoke()
            if (isChecked) {
                createStrikeThrough(buttonView)
            } else {
                removeStrikeThrough(buttonView)
            }
        }
    }

    fun createStrikeThrough(textView: TextView) {
        textView.paintFlags = textView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
    }

    fun removeStrikeThrough(textView: TextView) {
        textView.paintFlags = textView.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
    }
}
