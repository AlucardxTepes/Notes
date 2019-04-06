package com.alucard.notes.create

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.alucard.notes.R
import com.alucard.notes.foundations.ApplicationScope
import com.alucard.notes.foundations.NullFieldChecker
import com.alucard.notes.foundations.StateChangeTextWatcher
import com.alucard.notes.models.Task
import com.alucard.notes.models.Todo
import com.alucard.notes.tasks.TaskLocalModel
import com.alucard.notes.views.CreateTodoView
import kotlinx.android.synthetic.main.fragment_create_task.*
import kotlinx.android.synthetic.main.view_create_task.view.*
import kotlinx.android.synthetic.main.view_create_todo.view.*
import toothpick.Toothpick
import javax.inject.Inject

private const val MAX_TODO_COUNT = 5

class CreateTaskFragment : Fragment() {

    private var listener: OnFragmentInteractionListener? = null
    @Inject lateinit var model: TaskLocalModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Toothpick.inject(this, ApplicationScope.scope)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_task, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        createTaskView.taskEditText.addTextChangedListener(object : StateChangeTextWatcher() {
            override fun afterTextChanged(s: Editable?) {
                // Add if s is not empty or if s has changed from empty to not empty
                if (!s.isNullOrEmpty() && previousValue.isNullOrEmpty()) {
                    addTodoView()
                }
                super.afterTextChanged(s)
            }
        })
    }

    private fun addTodoView() {
        if (canAddTodo()) {
            val view =
                LayoutInflater.from(context).inflate(R.layout.view_create_todo, containerView, false) as CreateTodoView
            view.todoEditText.addTextChangedListener(object : StateChangeTextWatcher() {
                override fun afterTextChanged(s: Editable?) {
                    if (!s.isNullOrEmpty() && previousValue.isNullOrEmpty()) {
                        addTodoView()
                    } else if (!previousValue.isNullOrEmpty() && s.isNullOrEmpty()) {
                        removeTodoView(view)
                    }
                    super.afterTextChanged(s)
                }
            })
            containerView.addView(view)
        }
    }

    private fun removeTodoView(view: View) {
        containerView.removeView(view)
    }

    private fun canAddTodo(): Boolean = (containerView.childCount <= MAX_TODO_COUNT) && !(containerView.getChildAt(containerView.childCount - 1) as NullFieldChecker).hasNullField()

    private fun isTaskEmpty(): Boolean = createTaskView.taskEditText.editableText.isNullOrEmpty()

    fun saveTask(callback: (Boolean) -> Unit) {
        createTask()?.let {
            model.addTask(it) {
                // Assume model always works
                callback.invoke(true)
            }
        } ?: callback.invoke(false)

        if (!isTaskEmpty()) {

            containerView.run {

                var taskField: String? = null
                var todoList: MutableList<Todo> = mutableListOf()

                for (i in 0 until containerView.childCount) {

                    if (i == 0) {
                        // task
                        taskField = containerView.getChildAt(i).taskEditText?.toString()
                    } else {
                        // todo
                        if (containerView.getChildAt(i).todoEditText.editableText?.toString() != null) {
                            todoList.add(
                                Todo(containerView.getChildAt(i).todoEditText.editableText.toString())
                            )
                        }
                    }
                }
                taskField?.let {
                    model.addTask(Task(it, todoList)) {
                        // empty callback
                    }
                }
            }
        }
    }

    private fun createTask(): Task? {
        if (!isTaskEmpty()) {
            containerView.run {
                var taskField: String? = null
                val todoList: MutableList<Todo> = mutableListOf()

                for (i in 0 until containerView.childCount) {
                    if (i == 0) {
                        // task
                        taskField = containerView.getChildAt(i).taskEditText?.toString()
                    } else {
                        // todo
                        if (!containerView.getChildAt(i).todoEditText.editableText.isNullOrEmpty()) {
                            todoList.add(
                                Todo(containerView.getChildAt(i).todoEditText.editableText.toString())
                            )
                        }
                    }
                }
                return taskField?.let { Task(taskField, todoList) }
            }
        } else {
            return null
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        fun newInstance() = CreateTaskFragment()
    }
}
