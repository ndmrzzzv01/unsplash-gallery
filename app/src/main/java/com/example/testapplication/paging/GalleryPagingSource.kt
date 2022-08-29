package com.example.testapplication.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.testapplication.network.GalleryRepository
import com.example.testapplication.network.data.Gallery

class GalleryPagingSource(
    private val galleryRepository: GalleryRepository
) : PagingSource<Int, Gallery>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Gallery> {
        val nextPageNumber = params.key ?: 1
        return try {
            val photos = galleryRepository.getPhotos(nextPageNumber)
            val nextKey = if (photos.isEmpty()) null
            else nextPageNumber + 1

            LoadResult.Page(
                data = photos,
                prevKey = if (nextPageNumber == 1) null else nextPageNumber,
                nextKey = nextKey
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Gallery>): Int? = state.anchorPosition?.let {
        val anchorPage = state.closestPageToPosition(it)
        return anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
    }
}