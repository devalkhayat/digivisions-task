package com.digivisions.posts.features.articles.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.digivisions.posts.R
import com.digivisions.posts.databinding.FragmentDetailsBinding
import com.digivisions.posts.databinding.FragmentHomeBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class DetailsFragment : BottomSheetDialogFragment() {

    private val TAG = "DetailsFragment"
    private lateinit var binding: FragmentDetailsBinding
    private val args: DetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        loadData()
        return binding.root
    }
    private fun loadData() {
        args.selectedArticle.apply {
            display(title, urlToImage)
        }
    }
     fun display(title:String,imageUrl:String?):Boolean {
        binding.apply {
            if(title.isNotEmpty() && imageUrl!!.isNotEmpty()) {
                tvTitle.text = title
                Glide.with(requireContext()).load(imageUrl)
                    .placeholder(com.digivisions.resources.R.color.gray) // placeholder
                    .error(com.digivisions.resources.R.color.gray) // image broken
                    .fallback(com.digivisions.resources.R.color.gray) // no image
                    .into(img)
                return  true
            }else{
                return false
            }
        }
    }


}

