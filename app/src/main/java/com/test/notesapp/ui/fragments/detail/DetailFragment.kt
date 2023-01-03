package com.test.notesapp.ui.fragments.detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.test.notesapp.R
import com.test.notesapp.databinding.FragmentDetailBinding
import com.test.notesapp.util.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment: Fragment(R.layout.fragment_detail){
    private var fragmentBinding : FragmentDetailBinding? = null
    private lateinit var viewModel : DetailViewModel
    private val args: DetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[DetailViewModel::class.java]
        fragmentBinding = FragmentDetailBinding.bind(view)
        subscribeToObservers()
        val noteId = args.noteId
        viewModel.getNoteById(noteId)
    }

    private fun subscribeToObservers() {
        viewModel.getNoteResult().observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    fragmentBinding?.title?.text = it.data?.title
                    fragmentBinding?.subTitle?.text = it.data?.body
                }
                Status.ERROR -> {
                    Toast.makeText(requireContext(), it.message ?: "Error", Toast.LENGTH_LONG)
                        .show()
                }

            }
        }
    }
}