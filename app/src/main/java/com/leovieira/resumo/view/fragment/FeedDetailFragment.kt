package com.leovieira.resumo.view.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import com.leovieira.resumo.R
import com.leovieira.resumo.databinding.FeedDetailFragmentBinding
import com.leovieira.resumo.viewmodel.FeedDetailViewModel

class FeedDetailFragment : Fragment(R.layout.feed_detail_fragment) {

    private lateinit var viewModel: FeedDetailViewModel
    private lateinit var binding: FeedDetailFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FeedDetailFragmentBinding.bind(view)
        viewModel = ViewModelProvider(this).get(FeedDetailViewModel::class.java)

        binding.closeButton.setOnClickListener{
            findNavController().navigate(R.id.action_feedDetailFragment_to_accountFragment)
        }
    }

}