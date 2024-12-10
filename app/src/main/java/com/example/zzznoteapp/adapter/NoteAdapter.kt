package com.example.zzznoteapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.zzznoteapp.R
import com.example.zzznoteapp.model.NoteModel


class NoteAdapter(
    private val items: List<NoteModel>,
    private val onItemClick: (NoteModel) -> Unit) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
        inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            private val title = itemView.findViewById<TextView>(R.id.NoteTitle)
            private val description = itemView.findViewById<TextView>(R.id.NoteDescription)

            fun bind(note: NoteModel) {
                title.text = note.title
                description.text = note.description

                itemView.setOnClickListener {
                    onItemClick(note)
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_cardnote, parent, false)
            return NoteViewHolder(view)
        }

        override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
            holder.bind(items[position])
        }

        override fun getItemCount(): Int {
            return items.size
        }
    }
