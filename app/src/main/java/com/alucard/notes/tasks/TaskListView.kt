package com.alucard.notes.tasks

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.alucard.notes.models.Task
import kotlinx.android.synthetic.main.fragment_tasks_list.view.*

class TaskListView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 1
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private lateinit var adapter: TaskAdapter
    private lateinit var touchActionDelegate: TasksListFragment.TouchActionDelegate
    private lateinit var dataActionDelegate: TaskListViewContract

    fun initView(taDelegate: TasksListFragment.TouchActionDelegate, daDelegate: TaskListViewContract) {
        setDelegates(taDelegate, daDelegate)
        setupView()
    }

    private fun setDelegates(taDelegate: TasksListFragment.TouchActionDelegate, daDelegate: TaskListViewContract) {
        touchActionDelegate = taDelegate
        dataActionDelegate = daDelegate
    }

    private fun setupView() {
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = TaskAdapter(
            touchActionDelegate = touchActionDelegate,
            dataActionDelegate = dataActionDelegate
        )
        recyclerView.adapter = adapter
    }

    fun updateList(list: List<Task>) {
        adapter.updateList(list)
    }

}