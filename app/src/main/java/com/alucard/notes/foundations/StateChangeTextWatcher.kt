package com.alucard.notes.foundations

import android.text.Editable
import android.text.TextWatcher

open class StateChangeTextWatcher : TextWatcher {

    var previousValue: String? = null

    override fun afterTextChanged(s: Editable?) {
        previousValue = s?.toString()
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        // No operation
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        // No operation
    }
}