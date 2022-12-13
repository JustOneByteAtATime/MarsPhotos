package com.example.android.marsphotos.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.example.android.marsphotos.network.MarsPhoto
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.marsphotos.databinding.GridViewItemBinding

class PhotoGridAdapter : ListAdapter<MarsPhoto,
        PhotoGridAdapter.MarsPhotoViewHolder>(DiffCallback)
{
    companion object DiffCallback : DiffUtil.ItemCallback<MarsPhoto>() {
        override fun areItemsTheSame(oldItem: MarsPhoto, newItem: MarsPhoto): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MarsPhoto, newItem: MarsPhoto): Boolean {
            return oldItem.imgSrcUrl == newItem.imgSrcUrl
        }
    }

    class MarsPhotoViewHolder(private var binding:
                              GridViewItemBinding):
        RecyclerView.ViewHolder(binding.root)
    {
        fun bind(MarsPhoto: MarsPhoto) {
            binding.photo = MarsPhoto
            binding.executePendingBindings()
        }

    }

// Still inside the PhotoGridAdapter class in onCreateViewHolder(), remove the TO-DO and add the line
// shown below. The onCreateViewHolder() method needs to return a new MarsPhotoViewHolder, created by
// inflating the GridViewItemBinding and using the LayoutInflater from your parent ViewGroup context.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoGridAdapter.MarsPhotoViewHolder
    {
        return MarsPhotoViewHolder(GridViewItemBinding.inflate(
            LayoutInflater.from(parent.context)))
    }

    // In the onBindViewHolder() method, remove the TO-DO and add the lines shown below. Here you call
    // getItem() to get the MarsPhoto object associated with the current RecyclerView position, and
    // then pass that property to the bind() method in the MarsPhotoViewHolder.

    override fun onBindViewHolder(holder: PhotoGridAdapter.MarsPhotoViewHolder, position: Int) {
        val marsPhoto = getItem(position)
        holder.bind(marsPhoto)
    }

}
