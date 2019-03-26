package com.alucard.notes.notes


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.alucard.notes.R
import kotlinx.android.synthetic.main.fragment_notes_list.view.*

class NotesListFragment : Fragment() {

    lateinit var adapter: NoteAdapter
    lateinit var viewModel: NoteViewModel
    lateinit var touchActionDelegate: TouchActionDelegate

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        context?.let {
            if (it is TouchActionDelegate) {
                touchActionDelegate = it
            }
        }
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
        adapter = NoteAdapter(touchActionDelegate = touchActionDelegate)
        view.recyclerView.adapter = adapter
        bindViewModel()
    }

    private fun bindViewModel() {
        viewModel = ViewModelProviders.of(this).get(NoteViewModel::class.java)

        viewModel.noteLiveData.observe(this, Observer { noteList -> adapter.updateList(noteList) })
    }

    companion object {
        fun newInstance() = NotesListFragment()
    }

    interface TouchActionDelegate {
        fun onAddButtonClicked(value: String)
    }
}
