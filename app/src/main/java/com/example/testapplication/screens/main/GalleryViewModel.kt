package com.example.testapplication.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.testapplication.network.GalleryRepository
import com.example.testapplication.paging.GalleryPagingSource

class GalleryViewModel : ViewModel() {

    private val repository = GalleryRepository()

    val flow = Pager(PagingConfig(9)) {
        GalleryPagingSource(repository)
    }.flow.cachedIn(viewModelScope)

}