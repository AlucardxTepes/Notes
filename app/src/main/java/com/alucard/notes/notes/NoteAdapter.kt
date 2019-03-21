package com.alucard.notes.notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alucard.notes.R
import com.alucard.notes.foundations.BaseRecyclerAdapter
import com.alucard.notes.models.Note
import com.alucard.notes.views.NoteView

class NoteAdapter(noteList: MutableList<Note>): BaseRecyclerAdapter<Note>(noteList) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false))

    class ViewHolder(view: View) : BaseViewHolder<Note>(view) {
        
        override fun onBind(modelEntity: Note){
            (view as NoteView).initView(modelEntity)
        }
    }
}