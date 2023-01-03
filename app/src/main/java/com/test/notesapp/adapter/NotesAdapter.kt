package com.test.notesapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.test.notesapp.R
import com.test.notesapp.databinding.NoteRowBinding
import com.test.notesapp.roomdb.Note
import javax.inject.Inject

class NotesAdapter @Inject constructor(val listener: NotesAdapterListener
) : RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {


    class NoteViewHolder(val binding: NoteRowBinding) : RecyclerView.ViewHolder(binding.root)

    private val diffUtil = object : DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem == newItem
        }
    }

    private val recyclerListDiffer = AsyncListDiffer(this, diffUtil)

    var notes: List<Note>
        get() = recyclerListDiffer.currentList
        set(value) = recyclerListDiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding: NoteRowBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.note_row,
                parent,
                false
            )
        return NoteViewHolder(binding)
    }


    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val item = notes[position]
        holder.binding.title.text = item.title
        item.body?.let { holder.binding.subTitle.text = it }
        holder.itemView.setOnClickListener { listener.noteClick(item.id!!) }
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    interface NotesAdapterListener {
        fun noteClick(noteId:Int)
    }
}