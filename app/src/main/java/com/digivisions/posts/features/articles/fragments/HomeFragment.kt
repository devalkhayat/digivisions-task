package com.digivisions.posts.features.articles.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.map
import androidx.recyclerview.widget.LinearLayoutManager
import com.digivisions.domain.articles.models.Article
import com.digivisions.posts.R
import com.digivisions.posts.databinding.FragmentHomeBinding
import com.digivisions.posts.features.articles.adapters.ArticlesAdapter
import com.digivisions.posts.features.articles.viewmodels.ArticlesViewModel
import com.digivisions.posts.util.eventListners.RecycleViewEventListener
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(),RecycleViewEventListener {

    private  val TAG = "HomeFragment"
    private lateinit var binding:FragmentHomeBinding
    private  val articlesViewModel by viewModel<ArticlesViewModel>()
    private lateinit var articlesAdapter: ArticlesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentHomeBinding.inflate(inflater,container,false)
        init()
        return binding.root
    }

    private fun init(){
        articlesAdapter= ArticlesAdapter(this)
        binding.rvResult.adapter = articlesAdapter

        articlesViewModel.getArticles()

        articlesViewModel.articlesResponseLive.observe(viewLifecycleOwner) {
                viewLifecycleOwner.lifecycleScope.launch {
                    it.collect {
                        articlesAdapter.submitData(it)
                    }
                }
            }

    }

    @SuppressLint("SuspiciousIndentation")
    override fun onClick(obj: Any) {
        var action =HomeFragmentDirections.actionHomeFragmentToDetailsFragment(obj as Article)
        findNavController().navigate(action)
    }


}