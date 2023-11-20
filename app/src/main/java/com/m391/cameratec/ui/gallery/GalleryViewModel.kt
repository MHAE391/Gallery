package com.m391.cameratec.ui.gallery

import android.content.Context
import android.provider.MediaStore
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.m391.cameratec.R
import com.m391.cameratec.model.ImageModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GalleryViewModel : ViewModel() {
    private val _images = MutableLiveData<List<ImageModel>>()
    val images: LiveData<List<ImageModel>> = _images


    suspend fun fetchImages(context: Context) = withContext(Dispatchers.IO) {
        val projection = arrayOf(
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.DISPLAY_NAME,
            MediaStore.Images.Media.DATA
        )
        val imagesData = ArrayList<ImageModel>()
        imagesData.add(
            ImageModel(
                0,
                "Add New Image",
                "android.resource://${context.packageName}/${R.drawable.add_image}"
            )
        )
        val cursor = context.contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            projection,
            null,
            null,
            null
        )

        cursor?.use { theCursor ->
            val idColumn = theCursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
            val nameColumn = theCursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME)
            val dataColumn = theCursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)

            while (theCursor.moveToNext()) {
                val id = theCursor.getLong(idColumn)
                val name = theCursor.getString(nameColumn)
                val path = theCursor.getString(dataColumn)
                val image = ImageModel(id, name, path)
                imagesData.add(image)
                _images.postValue(imagesData)
            }
        }
    }
}