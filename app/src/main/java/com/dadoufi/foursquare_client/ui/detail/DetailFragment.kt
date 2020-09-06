package com.dadoufi.foursquare_client.ui.detail

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.dadoufi.foursquare_client.R
import com.dadoufi.foursquare_client.databinding.FragmentDetailBinding
import com.dadoufi.foursquare_client.utils.observeK
import com.dadoufi.foursquare_client.utils.setMarginTop
import dagger.hilt.android.AndroidEntryPoint
import dev.chrisbanes.insetter.Insetter
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@FlowPreview
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {

    private val viewModel by viewModels<DetailViewModel>()
    private lateinit var binding: FragmentDetailBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding = FragmentDetailBinding.bind(view)
        binding.facebook.movementMethod = LinkMovementMethod.getInstance()
        binding.run {
                Insetter.builder().setOnApplyInsetsListener { view, insets, _ ->
                    toolbar.setMarginTop(insets.systemWindowInsetTop)
                    statusBarGradientView.minimumHeight =
                        insets.systemWindowInsetTop
                }.applyToView(this.root)

        }

        viewModel.viewState.observeK(this) {
            binding.viewState = it
        }
    }

}

