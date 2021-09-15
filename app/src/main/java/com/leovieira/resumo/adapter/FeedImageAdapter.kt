package com.leovieira.resumo.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.leovieira.resumo.R
import com.leovieira.resumo.databinding.FeedFragmentBinding
import com.leovieira.resumo.databinding.FeedItemBinding
import com.leovieira.resumo.model.Image

class FeedImageAdapter : ListAdapter<Image, FeedImageViewHolder>(ImageDiffUtilItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedImageViewHolder {
        LayoutInflater.from(parent.context).inflate(R.layout.feed_item, parent, false).apply {
            return FeedImageViewHolder(this)
        }
    }

    override fun onBindViewHolder(holder: FeedImageViewHolder, position: Int) {
        getItem(position).let { image ->
            holder.bind(image)
        }
    }
}

class FeedImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = FeedItemBinding.bind(view)

    fun bind(image: Image) {

        binding.textViewName.text = image.user
        Glide.with(itemView).load(image.userImageURL).into(binding.imageViewAvatar)
        binding.textViewLikes.text = image.likes.toString()

        binding.bottomBarInfo.visibility = View.GONE
        Glide.with(itemView)
            .load(image.largeImageURL)
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    p0: GlideException?,
                    p1: Any?,
                    p2: Target<Drawable>?,
                    p3: Boolean
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    p0: Drawable?,
                    p1: Any?,
                    p2: Target<Drawable>?,
                    p3: DataSource?,
                    p4: Boolean
                ): Boolean {
                    binding.bottomBarInfo.visibility = View.VISIBLE
                    return false
                }
            })
            .into(binding.imageViewPhoto)

    }

}