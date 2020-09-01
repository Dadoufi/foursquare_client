package com.dadoufi.foursquare_client.search

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import com.dadoufi.foursquare_client.R
import com.dadoufi.foursquare_client.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search) {

    private val viewModel by activityViewModels<SearchViewModel>()
    private lateinit var binding: FragmentSearchBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentSearchBinding.bind(view)

        viewModel.venuesList.observe(viewLifecycleOwner) {

        }

    }


}