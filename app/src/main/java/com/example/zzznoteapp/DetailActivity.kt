package com.example.zzznoteapp

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.zzznoteapp.database.NoteDatabase
import com.google.android.material.floatingactionbutton.FloatingActionButton

class DetailActivity : AppCompatActivity() {

    private lateinit var database: NoteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailnote)

        val id = intent.getIntExtra("id", 0)
        val title = intent.getStringExtra("title") ?: "No Title"
        val description = intent.getStringExtra("description") ?: "No Description"

        findViewById<TextView>(R.id.NoteTitleDetail).text = title
        findViewById<TextView>(R.id.NoteDescriptionDetail).text = description

        // Tombol Delete
        val deleteButton : FloatingActionButton = findViewById(R.id.DeleteNoteButton)
        deleteButton.setOnClickListener(){
            database = NoteDatabase.getDatabase(this)
            database.noteDao().deleteDataById(id)
            finish()
        }

        // Tombol Edit
        val editButton : FloatingActionButton = findViewById(R.id.EditNoteButton)
        editButton.setOnClickListener(){
            val intent = intent
            intent.setClass(this, AddNoteActivity::class.java)
            startActivity(intent)
        }
    }
}
