package com.example.noteapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.data.Note
import com.example.noteapp.databinding.NoteItemCardBinding

class NoteListAdapter(
    private val onClickCard: (note: Note) -> Unit = {},
    private val onDelete: (note: Note) -> Unit = {},
) :
    ListAdapter<Note, NoteListAdapter.NoteListViewHolder>(NoteDiffCallback()) {

    inner class NoteListViewHolder(private val binding: NoteItemCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(note: Note) {
            binding.apply {
                noteTitle.text = note.title
                noteBody.text = note.body
                ibDelete.setOnClickListener {
                    onDelete(note)
                }
            }

            binding.root.setOnClickListener {
                onClickCard(note)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteListViewHolder {

        return NoteListViewHolder(
            NoteItemCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: NoteListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}

class NoteDiffCallback() : DiffUtil.ItemCallback<Note>() {
    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem == newItem
    }

}