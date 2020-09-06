package com.dadoufi.foursquare_client.ui.search

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.dadoufi.foursquare_client.R
import com.dadoufi.foursquare_client.data.local.entities.VenuesEntity
import com.dadoufi.foursquare_client.databinding.FragmentSearchBinding
import com.dadoufi.foursquare_client.utils.observeK
import com.dadoufi.foursquare_client.utils.onQueryTextChanged
import com.dadoufi.foursquare_client.utils.setMarginTop
import dagger.hilt.android.AndroidEntryPoint
import dev.chrisbanes.insetter.Insetter
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search) {

    private val viewModel by viewModels<SearchViewModel>()
    private lateinit var binding: FragmentSearchBinding

    @Inject
    lateinit var searchController: SearchController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentSearchBinding.bind(view)
        binding.recyclerView.run {
            searchController.callbacks = object : SearchController.Callbacks {
                override fun onItemClicked(item: VenuesEntity) {
                    findNavController().navigate(
                        SearchFragmentDirections.actionMainFragmentToDetailFragment(item.venueId)
                    )
                }
            }
            setController(searchController)
        }
        binding.run {
            Insetter.builder().setOnApplyInsetsListener { _, insets, _ ->
                searchAppbar.setMarginTop(insets.systemWindowInsetTop)
            }
        }

        viewModel.viewState.observeK(requireActivity()) {
            searchController.setData(it)
        }


        binding.searchView.onQueryTextChanged {
            viewModel.setQuery(it)
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        viewModel.setQuery(binding.searchView.query.toString())
    }

}