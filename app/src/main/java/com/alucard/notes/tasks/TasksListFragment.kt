package com.alucard.notes.tasks


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.alucard.notes.R
import com.alucard.notes.models.Task
import kotlinx.android.synthetic.main.fragment_tasks_list.*


class TasksListFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tasks_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = TaskAdapter(mutableListOf(
            Task("Task1"),
            Task("Task2")
        ))
        recyclerView.adapter = adapter
    }

    companion object {
        fun newInstance() = TasksListFragment()
    }
}
