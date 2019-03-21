package com.alucard.notes.notes


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.alucard.notes.R
import com.alucard.notes.models.Note
import kotlinx.android.synthetic.main.fragment_notes_list.view.*

class NotesListFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notes_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.recyclerView.layoutManager = LinearLayoutManager(context)
        val noteAdapter = NoteAdapter(mutableListOf(
            Note("Note1"),
            Note("Note2"),
            Note("Note3")
        ))
        view.recyclerView.adapter = noteAdapter
    }

    companion object {
        fun newInstance() = NotesListFragment()
    }
}