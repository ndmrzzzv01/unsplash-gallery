package com.example.testapplication.screens.photodetail

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.testapplication.databinding.FragmentPhotoBinding
import com.example.testapplication.network.Loading

class PhotoFragment : Fragment() {

    companion object {
        private const val URL = "url"
        fun newInstance(url: String): PhotoFragment {
            val fragment = PhotoFragment()
            fragment.arguments = Bundle().apply {
                putString(URL, url)
            }
            return fragment
        }
    }

    var loading: Loading? = null
    private lateinit var binding: FragmentPhotoBinding
    private var url: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        url = arguments?.getString(URL)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        loading = context as Loading
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPhotoBinding.inflate(inflater, container, false)

        initViews()

        return binding.root
    }

    override fun onDetach() {
        super.onDetach()
        loading = null
    }

    private fun initViews() {
        Glide
            .with(binding.root)
            .load(url)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    loading?.hideLoading()
                    binding.tvErrorMessage.visibility = View.VISIBLE
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    loading?.hideLoading()
                    return false
                }

            })
            .into(binding.image)
    }

}