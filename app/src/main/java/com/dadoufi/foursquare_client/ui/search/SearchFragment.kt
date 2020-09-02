package com.dadoufi.foursquare_client.ui.search

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.dadoufi.foursquare_client.R
import com.dadoufi.foursquare_client.data.local.entities.VenuesEntity
import com.dadoufi.foursquare_client.databinding.FragmentSearchBinding
import com.dadoufi.foursquare_client.utils.observeK
import com.dadoufi.foursquare_client.utils.onQueryTextChanged
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search) {

    private val viewModel by activityViewModels<SearchViewModel>()
    private lateinit var binding: FragmentSearchBinding

    @Inject
    lateinit var searchController: SearchController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentSearchBinding.bind(view)
        binding.recyclerView.run {
            searchController.callbacks = object : SearchController.Callbacks {
                override fun onItemClicked(item: VenuesEntity) {
                    Toast.makeText(requireContext(), item.toString(), Toast.LENGTH_SHORT).show()
                }

            }
            setController(searchController)
        }

        binding.searchView.onQueryTextChanged {
            viewModel.setQuery(it)
        }

        viewModel.viewState.observeK(this) {
            searchController.setData(it)
        }
    }


}