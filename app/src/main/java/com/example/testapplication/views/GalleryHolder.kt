package com.example.testapplication.views

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testapplication.databinding.ItemListBinding
import com.example.testapplication.network.data.Gallery
import com.example.testapplication.screens.main.GalleryFragment

class GalleryHolder(
    private val binding: ItemListBinding,
    private val listener: GalleryFragment.Listener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(photo: Gallery) {
        binding.apply {
            Glide.with(root).load(photo.url?.smallPhoto).into(imageView)
            imageView.setOnClickListener {
                listener.onPhotoClick(photo.url?.fullPhoto ?: "")
            }

            if (photo.description == null) {
                tvNameOfPicture.text = "Unknown"
            } else tvNameOfPicture.text = photo.description

            tvAuthor.text = photo.user?.name
        }
    }

}