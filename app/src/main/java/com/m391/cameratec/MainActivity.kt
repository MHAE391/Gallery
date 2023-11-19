package com.m391.cameratec

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.m391.cameratec.databinding.ActivityMainBinding
import com.m391.cameratec.databinding.FragmentGalleryBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}