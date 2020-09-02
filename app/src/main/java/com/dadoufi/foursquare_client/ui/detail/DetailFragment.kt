package com.dadoufi.foursquare_client.ui.detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.dadoufi.foursquare_client.R
import com.dadoufi.foursquare_client.data.local.entities.VenuesEntity
import com.dadoufi.foursquare_client.databinding.FragmentDetailBinding
import com.dadoufi.foursquare_client.utils.observeK
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {

    private val viewModel by activityViewModels<DetailViewModel>()
    private lateinit var binding: FragmentDetailBinding

    @Inject
    lateinit var detailController: DetailController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentDetailBinding.bind(view)
        binding.recyclerView.run {
            detailController.callbacks = object : DetailController.Callbacks {
                override fun onItemClicked(item: VenuesEntity) {
                    Toast.makeText(requireContext(), item.toString(), Toast.LENGTH_SHORT).show()
                }

            }
            setController(detailController)
        }

        viewModel.viewState.observeK(this) {
            detailController.setData(it)
        }
    }


}