package com.m391.cameratec.utils

import androidx.databinding.BindingAdapter
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import com.m391.cameratec.model.ImageModel
import kotlinx.coroutines.runBlocking


class DataBindingViewHolder<T>(private val binding: ViewDataBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: T) {
        when (item) {
            is ImageModel -> binding.setVariable(BR.image, item)
        }
        binding.executePendingBindings()
    }
}