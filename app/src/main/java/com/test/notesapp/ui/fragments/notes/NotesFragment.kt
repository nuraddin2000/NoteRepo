package com.test.notesapp.ui.fragments.notes

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.notesapp.R
import com.test.notesapp.adapter.NotesAdapter
import com.test.notesapp.databinding.FragmentNotesBinding
import com.test.notesapp.ui.fragments.detail.DetailFragmentArgs
import com.test.notesapp.util.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotesFragment: Fragment(R.layout.fragment_notes),NotesAdapter.NotesAdapterListener  {
    private var fragmentBinding : FragmentNotesBinding? = null
    private lateinit var viewModel : NotesViewModel
    private lateinit var noteRecyclerAdapter: NotesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[NotesViewModel::class.java]
        fragmentBinding = FragmentNotesBinding.bind(view)
        initRecyclerview()
        subscribeToObservers()
        viewModel.getNotes()
        fragmentBinding?.addButton?.setOnClickListener { findNavController().navigate(R.id.action_notesFragment_to_insertNoteFragment) }
    }

    private fun subscribeToObservers() {
        viewModel.getNotesResult().observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    noteRecyclerAdapter.notes = it.data!!
                }
                Status.ERROR -> {
                    Toast.makeText(requireContext(), it.message ?: "Error", Toast.LENGTH_LONG)
                        .show()
                }

            }
        }
    }

    private fun initRecyclerview() {
        noteRecyclerAdapter = NotesAdapter(this)
        val manager = LinearLayoutManager(requireContext(),RecyclerView.VERTICAL,false)
        fragmentBinding?.recyclerview?.layoutManager = manager
        fragmentBinding?.recyclerview?.adapter = noteRecyclerAdapter
    }

    override fun noteClick(noteId: Int) {
        val action = NotesFragmentDirections.actionNotesFragmentToDetailFragment(noteId)
        findNavController().navigate(action)
    }
}