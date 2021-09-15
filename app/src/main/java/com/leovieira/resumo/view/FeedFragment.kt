package com.leovieira.resumo.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.leovieira.resumo.R
import com.leovieira.resumo.adapter.FeedImageAdapter
import com.leovieira.resumo.databinding.FeedFragmentBinding
import com.leovieira.resumo.model.Image
import com.leovieira.resumo.viewmodel.FeedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FeedFragment : Fragment(R.layout.feed_fragment) {

    private lateinit var viewModel: FeedViewModel
    private lateinit var binding: FeedFragmentBinding
    private val adapterFeed = FeedImageAdapter()

    private val observerImages = Observer<List<Image>> {
        adapterFeed.submitList(it)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FeedFragmentBinding.bind(view)
        viewModel = ViewModelProvider(this).get(FeedViewModel::class.java)
        viewModel.images.observe(viewLifecycleOwner, observerImages)

        setupRecyclerView()

        binding.goToDetailsButton.setOnClickListener {
            findNavController().navigate(R.id.action_feedFragment_to_feedDetailFragment)
        }

    }

    private fun setupRecyclerView() = with(binding.recyclerViewFeed) {

        adapter = adapterFeed
        layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        viewModel.fetchImages()

    }

}