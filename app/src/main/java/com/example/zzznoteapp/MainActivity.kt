package com.example.zzznoteapp


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.AppCompatImageButton
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.zzznoteapp.adapter.NoteAdapter
import com.example.zzznoteapp.database.NoteDatabase
import com.google.android.material.button.MaterialButton
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var database: NoteDatabase
    private val currentTheme = AppCompatDelegate.getDefaultNightMode()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toggleThemeButton : MaterialButton = findViewById(R.id.ThemeSwitcherButton)

        if (currentTheme == AppCompatDelegate.MODE_NIGHT_YES) {
            toggleThemeButton.setIconResource(R.drawable.half_moon)
        } else {
            toggleThemeButton.setIconResource(R.drawable.sun_light)
        }

        toggleThemeButton.setOnClickListener {
            toggleTheme()
        }

        database = NoteDatabase.getDatabase(this)

    val recyclerView = findViewById<RecyclerView>(R.id.NoteList)
        LinearLayoutManager(/* context = */ this).also { recyclerView.layoutManager = it }

        database.noteDao().getAll().observe(this) {
            recyclerView.adapter = NoteAdapter(it) {
                val intent = Intent(this, DetailActivity::class.java).apply {
                    putExtra("id", it.id)
                    putExtra("title", it.title)
                    putExtra("description", it.description)
                }
                startActivity(intent)
            }
        }

        val addButton : FloatingActionButton = findViewById(R.id.AddNoteButton)
        addButton.setOnClickListener {
            val intent = Intent(this, AddNoteActivity::class.java)
            startActivity(intent)
        }
    }

    private fun toggleTheme(){
        if (currentTheme == AppCompatDelegate.MODE_NIGHT_YES) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
    }
}