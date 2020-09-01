package com.dadoufi.foursquare_client.ui.common

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.binaryfork.spanny.Spanny


@BindingAdapter("spannable")
fun setContactSpannable(view: TextView, location: List<String>) {
    val spanny = Spanny()
    location.forEachIndexed { index, s ->
        val delimiter = if (index == 0) "" else "\n"
        spanny.append(delimiter.plus(s))
    }
    view.text = spanny
}