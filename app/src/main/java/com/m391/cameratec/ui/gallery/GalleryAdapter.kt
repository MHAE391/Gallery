package com.m391.cameratec.ui.gallery

import com.m391.cameratec.R
import com.m391.cameratec.model.ImageModel
import com.m391.cameratec.utils.BaseRecyclerViewAdapter

class GalleryAdapter(callback: (image: ImageModel) -> Unit) :
    BaseRecyclerViewAdapter<ImageModel>(callback) {
    override fun getLayoutRes(viewType: Int): Int = R.layout.image_card
}