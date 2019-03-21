package com.alucard.notes.create

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alucard.notes.R
import com.alucard.notes.navigation.NavigationActivity
import kotlinx.android.synthetic.main.activity_create.*

class CreateActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)

        textView.text = intent.getStringExtra(NavigationActivity.FRAGMENT_TYPE_KEY)
    }
}
