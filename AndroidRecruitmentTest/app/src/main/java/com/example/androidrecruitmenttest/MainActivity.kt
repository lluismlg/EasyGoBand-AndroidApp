package com.example.androidrecruitmenttest

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

const val EXTRA_MESSAGE = "com.example.androidrecruitmenttest.MESSAGE"

class MainActivity : AppCompatActivity() {

    lateinit var buttonSubmit: Button
    lateinit var editTextID: EditText
    lateinit var eventID: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Edit actionbar
        val mainActionbar = supportActionBar
        mainActionbar!!.title = "Android Recruitment Test"

        buttonSubmit = findViewById(R.id.Main_button_submit)
        editTextID = findViewById(R.id.Main_editText_accessGroup)

        buttonSubmit.setOnClickListener {
            eventID = editTextID.text.toString()
            val intent = Intent(this, ListActivity::class.java).apply {
                putExtra(EXTRA_MESSAGE, eventID)
            }
            startActivity(intent)
        }
    }
}