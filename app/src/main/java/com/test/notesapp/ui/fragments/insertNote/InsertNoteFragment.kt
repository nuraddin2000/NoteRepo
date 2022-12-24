package com.test.notesapp.ui.fragments.insertNote

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.test.notesapp.R
import com.test.notesapp.databinding.FragmentInsertNoteBinding
import com.test.notesapp.roomdb.Note
import com.test.notesapp.util.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InsertNoteFragment : Fragment(R.layout.fragment_insert_note) {
    private var fragmentBinding: FragmentInsertNoteBinding? = null
    private lateinit var viewModel: InsertNoteViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[InsertNoteViewModel::class.java]
        fragmentBinding = FragmentInsertNoteBinding.bind(view)
        subscribeToObservers()
        fragmentBinding?.saveButton?.setOnClickListener {
            checkFields()
        }
    }

    private fun checkFields() {
        val note = Note()
        val title = fragmentBinding?.title?.text.toString()
        val body = fragmentBinding?.body?.text.toString()
        if (title.isEmpty()) {
            Toast.makeText(requireActivity(), "Title must not be empty", Toast.LENGTH_LONG).show()
        } else {
            note.title = title
            if (body.isNotEmpty()) {
                note.body = body
            }
            viewModel.insertNote(note)
        }
    }


    private fun subscribeToObservers() {
        viewModel.getInsertNoteMsg().observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    Log.d("TAG", "subscribeToObservers: ")
                    Toast.makeText(requireActivity(), "Success", Toast.LENGTH_LONG).show()
                    findNavController().popBackStack()
                }
                Status.ERROR -> {
                    Toast.makeText(requireContext(), it.message ?: "Error", Toast.LENGTH_LONG)
                        .show()
                }

            }
        }
    }


}