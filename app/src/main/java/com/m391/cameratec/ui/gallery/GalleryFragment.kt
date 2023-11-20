package com.m391.cameratec.ui.gallery

import android.content.Context
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.m391.cameratec.R
import com.m391.cameratec.databinding.FragmentGalleryBinding
import com.m391.cameratec.model.ImageModel
import com.m391.cameratec.utils.setupGridRecycler
import com.m391.cameratec.utils.setupLinearRecycler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GalleryFragment : Fragment() {
    private lateinit var binding: FragmentGalleryBinding
    private val viewModel: GalleryViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_gallery, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        setupRecyclerView()
        lifecycleScope.launch {
            viewModel.fetchImages(requireActivity())
        }

    }

    private fun setupRecyclerView() {
        val adapter = GalleryAdapter {
            if (it.id != 0.toLong())
                findNavController().navigate(
                    GalleryFragmentDirections.actionGalleryFragmentToViewerFragment(
                        it.uri
                    )
                )
            else findNavController().navigate(GalleryFragmentDirections.actionGalleryFragmentToCameraFragment())
        }
        binding.imagesRecyclerView.setupGridRecycler(adapter)
    }
}