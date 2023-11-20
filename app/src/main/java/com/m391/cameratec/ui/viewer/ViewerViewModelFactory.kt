package com.m391.cameratec.ui.viewer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewerViewModelFactory(private val imageUrl: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ViewerViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ViewerViewModel(imageUrl) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}