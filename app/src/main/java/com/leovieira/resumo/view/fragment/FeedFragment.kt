package com.leovieira.resumo.view.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.GridLayoutManager
import com.leovieira.resumo.R
import com.leovieira.resumo.adapter.FeedImageAdapter
import com.leovieira.resumo.adapter.HeaderAdapter
import com.leovieira.resumo.databinding.FeedFragmentBinding
import com.leovieira.resumo.model.Image
import com.leovieira.resumo.viewmodel.FeedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FeedFragment : Fragment(R.layout.feed_fragment) {

    private lateinit var viewModel: FeedViewModel
    private lateinit var binding: FeedFragmentBinding
    private var clearList = false
    private val adapterFeed = FeedImageAdapter()
    private val adapterHeader = HeaderAdapter{
        clearList = true
        viewModel.searchFor(q= it)
    }

    private val observerImages = Observer<List<Image>> {
        adapterFeed.update(it, clearList)
    }
    private val observerPage = Observer<Int>{
        viewModel.fetchImages(page = it)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FeedFragmentBinding.bind(view)
        viewModel = ViewModelProvider(this).get(FeedViewModel::class.java)
        viewModel.images.observe(viewLifecycleOwner, observerImages)
        viewModel.page.observe(viewLifecycleOwner, observerPage)

        setupRecyclerView()

        binding.nextButton.setOnClickListener{
            clearList = false
            viewModel.nextPage()
        }

//        binding.goToDetailsButton.setOnClickListener {
//            findNavController().navigate(R.id.action_feedFragment_to_feedDetailFragment)
//        }

    }

    private fun setupRecyclerView() = with(binding.recyclerViewFeed) {

        adapter = ConcatAdapter(adapterHeader, adapterFeed)
        layoutManager = GridLayoutManager(requireContext(), 2).apply {
            spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return if (position == 0) 2 else 1
                }
            }
        }
        viewModel.nextPage()

    }

}