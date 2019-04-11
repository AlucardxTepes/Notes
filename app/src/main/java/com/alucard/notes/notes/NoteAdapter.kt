package com.alucard.notes.notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alucard.notes.R
import com.alucard.notes.foundations.BaseRecyclerAdapter
import com.alucard.notes.models.Note
import com.alucard.notes.navigation.NavigationActivity
import com.alucard.notes.views.NoteView
import kotlinx.android.synthetic.main.view_add_button.view.*

class NoteAdapter(noteList: MutableList<Note> = mutableListOf(),
                  val touchActionDelegate: NotesListFragment.TouchActionDelegate,
                  val dataActionDelegate: NoteListViewContract)
    : BaseRecyclerAdapter<Note>(noteList) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        if (viewType == TYPE_INFO) {
            NoteViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false))
        } else {
            AddButtonViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_add_button, parent, false))
        }

    inner class NoteViewHolder(view: View) : BaseViewHolder<Note>(view) {
        override fun onBind(modelEntity: Note, listIndex: Int) {
            (view as NoteView).initView(modelEntity) {
                dataActionDelegate.onDeleteNote(dataList[listIndex])
            }
        }
    }

    inner class AddButtonViewHolder(view: View) : BaseRecyclerAdapter.AddButtonViewHolder(view) {
        override fun onBind(modelEntity: Unit, listIndex: Int) {
            view.buttonText.text = view.context.getString(R.string.add_button_note)
            view.setOnClickListener { touchActionDelegate.onAddButtonClicked(NavigationActivity.FRAGMENT_VALUE_NOTE) }
        }
    }
}