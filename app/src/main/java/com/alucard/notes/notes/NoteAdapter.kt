package com.alucard.notes.notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alucard.notes.R
import com.alucard.notes.models.Note
import kotlinx.android.synthetic.main.item_note.view.*

class NoteAdapter(private val noteList: MutableList<Note>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false))

    override fun getItemCount() = noteList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        (holder as ViewHolder).onBind(noteList[position])

    class ViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        
        fun onBind(note: Note){
            view.descriptionView.text = note.description
        }
    }
}