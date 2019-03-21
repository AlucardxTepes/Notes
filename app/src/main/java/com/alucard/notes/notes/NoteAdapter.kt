package com.alucard.notes.notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alucard.notes.R
import com.alucard.notes.foundations.BaseRecyclerAdapter
import com.alucard.notes.models.Note
import com.alucard.notes.views.NoteView
import kotlinx.android.synthetic.main.view_add_button.view.*

class NoteAdapter(noteList: MutableList<Note>) : BaseRecyclerAdapter<Note>(noteList) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        if (viewType == TYPE_ADD_BUTTON) {
            AddButtonViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_add_button, parent, false))
        } else {
            NoteViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false))
        }

    class NoteViewHolder(view: View) : BaseViewHolder<Note>(view) {
        override fun onBind(modelEntity: Note) {
            (view as NoteView).initView(modelEntity)
        }
    }

    class AddButtonViewHolder(view: View) : BaseRecyclerAdapter.BaseViewHolder<Unit>(view) {
        override fun onBind(modelEntity: Unit) {
            view.buttonText.text = view.context.getString(R.string.add_button_note)
        }
    }
}