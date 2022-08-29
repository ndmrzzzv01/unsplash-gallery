package com.example.testapplication.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.testapplication.databinding.ActivityMainBinding
import com.example.testapplication.network.Loading
import com.example.testapplication.screens.main.GalleryFragment
import com.example.testapplication.screens.photodetail.PhotoFragment

class MainActivity : AppCompatActivity(), GalleryFragment.Listener, Loading {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.view.setOnClickListener { }
        setContentView(binding.root)

        binding.apply {
            if (savedInstanceState == null) {
                supportFragmentManager
                    .beginTransaction()
                    .replace(binding.container.id, GalleryFragment())
                    .commit()
            }
        }
    }

    override fun onPhotoClick(url: String) {
        supportFragmentManager
            .beginTransaction()
            .replace(binding.container.id, PhotoFragment.newInstance(url))
            .addToBackStack(null)
            .commit()
    }

    override fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
        binding.view.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        binding.progressBar.visibility = View.GONE
        binding.view.visibility = View.GONE
    }
}