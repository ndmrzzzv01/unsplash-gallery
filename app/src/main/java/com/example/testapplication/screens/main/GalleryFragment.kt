package com.example.testapplication.screens.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testapplication.databinding.FragmentGalleryBinding
import com.example.testapplication.network.Loading
import com.example.testapplication.paging.ListLoadStateAdapter
import com.example.testapplication.utils.CommonUtil
import com.example.testapplication.views.GalleryAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class GalleryFragment : Fragment() {

    interface Listener {
        fun onPhotoClick(url: String)
    }

    var listener: Listener? = null
    var loading: Loading? = null
    private lateinit var binding: FragmentGalleryBinding
    private lateinit var galleryAdapter: GalleryAdapter
    private lateinit var concatAdapter: ConcatAdapter
    private val viewModel by viewModels<GalleryViewModel>()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as Listener
        loading = context as Loading
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGalleryBinding.inflate(inflater, container, false)

        checkInternetConnectionAndShowList()

        return binding.root
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
        loading = null
    }

    private fun checkInternetConnectionAndShowList() {
        if (!CommonUtil.isNetworkConnected(requireContext())) {
            binding.apply {
                rvGallery.visibility = View.INVISIBLE
                tvError.visibility = View.VISIBLE
                btnRetry.apply {
                    visibility = View.VISIBLE
                    setOnClickListener {
                        if (CommonUtil.isNetworkConnected(requireContext())) {
                            visibility = View.INVISIBLE
                            tvError.visibility = View.INVISIBLE
                            initRecyclerView()
                            initObservers()
                        } else {
                            return@setOnClickListener
                        }
                    }
                }
            }
        } else {
            initRecyclerView()
            initObservers()
        }
    }

    private fun initRecyclerView() {
        binding.apply {
            rvGallery.visibility = View.VISIBLE
            rvGallery.setHasFixedSize(true)
            galleryAdapter = GalleryAdapter(object : Listener {
                override fun onPhotoClick(url: String) {
                    loading?.showLoading()
                    listener?.onPhotoClick(url)
                }
            })
            concatAdapter = galleryAdapter.withLoadStateFooter(ListLoadStateAdapter())
            rvGallery.adapter = concatAdapter
            rvGallery.layoutManager = LinearLayoutManager(context)
        }
    }

    private fun initObservers() {
        lifecycleScope.launch {
            viewModel.flow.collectLatest {
                galleryAdapter.submitData(it)
            }
        }
    }

}