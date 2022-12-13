package com.example.android.marsphotos.network

import com.squareup.moshi.Json

// Make MarsPhoto a data class by adding the data keyword before the class definition. Change the {}
// braces to () parentheses. This leaves you with an error, because data classes must have at least
// one property defined.
data class MarsPhoto(
    // Replace the line for the img_src key with the line shown below.
    val id: String, @Json(name = "img_src") val imgSrcUrl: String
)