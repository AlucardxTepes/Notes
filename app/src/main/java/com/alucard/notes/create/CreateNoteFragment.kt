package com.alucard.notes.create

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.alucard.notes.R
import com.alucard.notes.foundations.ApplicationScope
import com.alucard.notes.foundations.NullFieldChecker
import com.alucard.notes.models.Note
import com.alucard.notes.notes.INoteModel
import kotlinx.android.synthetic.main.fragment_create_note.*
import toothpick.Toothpick
import javax.inject.Inject

class CreateNoteFragment : Fragment(), NullFieldChecker {

    private var listener: OnFragmentInteractionListener? = null

    @Inject
    lateinit var model: INoteModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Toothpick.inject(this, ApplicationScope.scope)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_note, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun hasNullField(): Boolean = noteEditText.editableText.isNullOrEmpty()

    fun saveNote(callback: (Boolean) -> Unit) {
        createNote()?.let {
            model.addNote(it) {
                // Assume model always works
                callback.invoke(true)
            }
        } ?: callback.invoke(false)
    }

    private fun createNote(): Note? {
        return if (!hasNullField()) {
            Note(noteEditText.editableText.toString())
        } else {
            null
        }
    }

    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        @JvmStatic
        fun newInstance() = CreateNoteFragment()
    }
}
