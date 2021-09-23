package com.hardbobby.miniimdb.presentation.main.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hardbobby.domain.dto.modelview.MovieItemModelView
import com.hardbobby.miniimdb.R
import com.hardbobby.miniimdb.databinding.ItemMovieListBinding
import com.hardbobby.miniimdb.presentation.common.Constants
import com.hardbobby.miniimdb.presentation.utils.GlideUtils
import setOnClickWithThrottle
import showOrHide

/**
 * Created by Bobby.Hardian on 22/09/2021.
 */
class ItemMovieAdapter(
    private val pageType: Constants.PageType,
    private val onLoadNextPage: () -> Unit,
    private val onFavoriteIconClicked: (Int, MovieItemModelView) -> Unit = { _, _ -> },
    private val onMovieClicked: (MovieItemModelView) -> Unit = { }
) : ListAdapter<MovieItemModelView, RecyclerView.ViewHolder>(MovieItemDiffCallback()) {
    class MovieItemDiffCallback : DiffUtil.ItemCallback<MovieItemModelView>() {
        override fun areItemsTheSame(
            oldItem: MovieItemModelView,
            newItem: MovieItemModelView
        ): Boolean = oldItem == newItem

        override fun areContentsTheSame(
            oldItem: MovieItemModelView,
            newItem: MovieItemModelView
        ): Boolean = oldItem == newItem
    }

    override fun submitList(list: List<MovieItemModelView>?) {
        super.submitList(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            ItemMovieListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding, pageType)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ItemViewHolder -> holder.bind(getItem(position))
        }
        if (position == currentList.size - 1) {
            onLoadNextPage()
        }
    }

    inner class ItemViewHolder(
        private val binding: ItemMovieListBinding,
        private val pageType: Constants.PageType
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(modelView: MovieItemModelView) {
            with(binding) {
                textViewTitleMovie.text = modelView.nameMovie
                textViewGenreMovie.text = modelView.genreIdList.joinToString(separator = ", ")
                imageViewFavoriteIcon.showOrHide(pageType == Constants.PageType.PopularPage)
                imageViewFavoriteIcon.setImageDrawable(
                    if (modelView.isFavourite) {
                        ContextCompat.getDrawable(itemView.context, R.drawable.ic_heart_filled)
                    } else {
                        ContextCompat.getDrawable(itemView.context, R.drawable.ic_heart_outline)
                    }
                )
                imageViewFavoriteIcon.setOnClickWithThrottle {
                    onFavoriteIconClicked(bindingAdapterPosition, modelView)
                }
                root.setOnClickWithThrottle {
                    onMovieClicked(modelView)
                }
                GlideUtils.getGlide(modelView.posterUrl, itemView.context)
                    .into(imageViewPosterMovie)
            }
        }
    }
}