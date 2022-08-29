package com.example.testapplication.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.testapplication.databinding.ItemListBinding
import com.example.testapplication.network.data.Gallery
import com.example.testapplication.screens.main.GalleryFragment

class GalleryAdapter(private val listener: GalleryFragment.Listener) :
    PagingDataAdapter<Gallery, GalleryHolder>(GalleryDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryHolder =
        GalleryHolder(
            ItemListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), listener
        )

    override fun onBindViewHolder(holder: GalleryHolder, position: Int) {
        getItem(position)?.let { photo ->
            holder.bind(photo)
        }
    }


}

class GalleryDiffUtil : DiffUtil.ItemCallback<Gallery>() {
    override fun areItemsTheSame(oldItem: Gallery, newItem: Gallery): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Gallery, newItem: Gallery): Boolean {
        return oldItem == newItem
    }
}