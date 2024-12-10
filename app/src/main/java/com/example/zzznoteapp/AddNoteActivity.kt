package com.example.zzznoteapp


import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.zzznoteapp.database.NoteDatabase
import com.example.zzznoteapp.model.NoteModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class AddNoteActivity : AppCompatActivity() {
    private lateinit var database: NoteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editornote)

        database = NoteDatabase.getDatabase(this)

        if ( this.intent.extras != null ) {
            val title = this.intent.getStringExtra("title")
            val description = this.intent.getStringExtra("description")

            findViewById<EditText>(R.id.NoteTitleInput).setText(title)
            findViewById<EditText>(R.id.NoteDescriptionInput).setText(description)
        }

        // Tombol Save
        val addButton : FloatingActionButton = findViewById(R.id.SaveNote)
        addButton.setOnClickListener {
            val title = findViewById<EditText>(R.id.NoteTitleInput).text.toString()
            val description = findViewById<EditText>(R.id.NoteDescriptionInput).text.toString()

            if (this.intent.extras != null) {
                val id = this.intent.getIntExtra("id", 0)
                val note = NoteModel(
                    id = id,
                    title = title,
                    description = description
                )
                database.noteDao().updateData(note)

                val intent = intent
                intent.setClass(this, MainActivity::class.java)
                startActivity(intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))

            } else if (title.isNotEmpty()) {
                val note = NoteModel(
                    title = title,
                    description = description
                )
                database.noteDao().insertData(note)
                finish()
            } else {
                Toast.makeText(this, "Please fill Title Field", Toast.LENGTH_SHORT).show()
            }
        }
    }
}