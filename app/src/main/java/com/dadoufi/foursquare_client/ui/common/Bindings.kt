package com.dadoufi.foursquare_client.ui.common

import android.text.method.LinkMovementMethod
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.core.text.HtmlCompat.FROM_HTML_MODE_LEGACY
import androidx.databinding.BindingAdapter
import coil.load
import com.binaryfork.spanny.Spanny
import com.dadoufi.foursquare_client.R
import com.dadoufi.foursquare_client.data.local.entities.VenueDetailsEntity
import com.dadoufi.foursquare_client.data.model.Contact
import com.dadoufi.foursquare_client.data.model.SocialType
import com.dadoufi.foursquare_client.data.model.getSocialUrl
import com.dadoufi.foursquare_client.ui.detail.DetailViewState
import com.dadoufi.foursquare_client.utils.hide
import com.dadoufi.foursquare_client.utils.show


@BindingAdapter("spannable")
fun TextView.setLocationSpannable(location: List<String>?) {
    location?.let {
        val spanny = Spanny()
        location.forEachIndexed { index, s ->
            val delimiter = if (index == 0) "" else "\n"
            spanny.append(delimiter.plus(s))
        }
        text = spanny
    }
}

@BindingAdapter("imageUrl")
fun ImageView.imageUrl(data: VenueDetailsEntity?) {
    data?.let {
        load(it.image) {
            crossfade(750)
            error(R.drawable.ic_no_image)
            fallback(R.drawable.ic_no_image)
                .size(resources.getDimensionPixelSize(R.dimen.error_image_size))
        }
    }
}

@BindingAdapter("socialText", "socialType")
fun TextView.socialText(contact: Contact?, socialType: SocialType) {
    movementMethod = LinkMovementMethod.getInstance()
    text = contact?.getSocialUrl(socialType)?.let { HtmlCompat.fromHtml(it, FROM_HTML_MODE_LEGACY) }
}

@BindingAdapter("showIf")
fun View.showIf(condition: Boolean) {
    if (condition) {
        show()
    } else {
        hide()
    }
}

@BindingAdapter("hideIf")
fun View.hideIf(condition: Boolean) {
    if (condition) {
        hide()
    } else {
        show()
    }
}

@BindingAdapter("hideOnLoading")
fun ViewGroup.hideOnLoading(viewState: DetailViewState?) {
    visibility = if (viewState is DetailViewState.Loading)
        View.GONE
    else
        View.VISIBLE
}

@BindingAdapter("showOnLoading")
fun ProgressBar.showOnLoading(viewState: DetailViewState?) {
    visibility = if (viewState is DetailViewState.Loading)
        View.VISIBLE
    else
        View.GONE
}

@BindingAdapter("showOnError")
fun TextView.showError(viewState: DetailViewState?) {
    visibility = if (viewState is DetailViewState.DetailError) {
        text = viewState.message
        View.VISIBLE
    } else {
        View.GONE
    }

}