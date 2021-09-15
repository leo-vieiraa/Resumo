package com.leovieira.resumo.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.leovieira.resumo.R
import com.leovieira.resumo.databinding.SplashFragmentBinding
import com.leovieira.resumo.viewmodel.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : Fragment(R.layout.splash_fragment) {

    companion object {
        fun newInstance() = SplashFragment()
    }

    private lateinit var viewModel: SplashViewModel
    private lateinit var binding: SplashFragmentBinding

    private val observerLoadingData = Observer<Boolean> {
        if (!it) {
            findNavController().navigate(R.id.action_splashFragment_to_feedFragment)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(SplashViewModel::class.java)
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()

        viewModel.isloading.observe(viewLifecycleOwner, observerLoadingData)
        viewModel.loadData()

    }

}