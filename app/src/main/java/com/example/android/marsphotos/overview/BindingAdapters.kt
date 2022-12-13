package com.example.android.marsphotos.overview

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.android.marsphotos.R
import com.example.android.marsphotos.network.MarsPhoto

// 4.1 In BindingAdapters.kt create a bindImage() function as a top-level function (not inside a class)
// that takes an ImageView and a String as parameters.

// 4.3 Annotate the function with @BindingAdapter. The @BindingAdapter annotation tells data binding
// to execute this binding adapter when a View item has the imageUrl attribute.

// 4.4 let is used to invoke one or more functions on results of call chains.
//
//The let function along with safe call operator( ?.) is used to perform a null safe operation on the
// object. In this case, the let code block will only be executed if the object is not null.

// 4.5 Inside the let{} block, add the following line to convert the URL string to a Uri object using
// the toUri() method. To use the HTTPS scheme, append buildUpon.scheme("https") to the toUri builder.
// Call build() to build the object.

// 4.6 Inside let{} block, after the imgUri declaration, use the load(){} from Coil, to load the image
// from the imgUri object into the imgView.

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?)
{
    imgUrl?.let{
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        imgView.load(imgUri)
        {
            placeholder(R.drawable.loading_animation)
            error(R.drawable.ic_broken_image)
        }

    }
}

class BindingAdapters {
}

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView,
                     data: List<MarsPhoto>?) {
    val adapter = recyclerView.adapter as PhotoGridAdapter
    adapter.submitList(data)

}

@BindingAdapter("marsApiStatus")
fun bindStatus(statusImageView: ImageView,
               status: MarsApiStatus?) {
    // Inside the when {}, add a case for the loading state (MarsApiStatus.LOADING). For this state,
    // set the ImageView to visible, and assign it the loading animation. This is the same animation
    // drawable you used for Coil in the previous task.
    when (status) {
        MarsApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        MarsApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        MarsApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }

