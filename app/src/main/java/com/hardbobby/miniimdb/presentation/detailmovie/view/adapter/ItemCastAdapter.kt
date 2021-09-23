package com.hardbobby.miniimdb.presentation.detailmovie.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hardbobby.domain.dto.modelview.MovieCastModelView
import com.hardbobby.miniimdb.databinding.ItemCastProfileBinding
import com.hardbobby.miniimdb.presentation.utils.GlideUtils

/**
 * Created by Bobby.Hardian on 23/09/2021.
 */
class ItemCastAdapter :
    ListAdapter<MovieCastModelView, RecyclerView.ViewHolder>(MovieCastDiffCallBack()) {

    class MovieCastDiffCallBack : DiffUtil.ItemCallback<MovieCastModelView>() {
        override fun areItemsTheSame(
            oldItem: MovieCastModelView,
            newItem: MovieCastModelView
        ): Boolean = oldItem == newItem

        override fun areContentsTheSame(
            oldItem: MovieCastModelView,
            newItem: MovieCastModelView
        ): Boolean = oldItem == newItem
    }

    override fun submitList(list: List<MovieCastModelView>?) {
        super.submitList(list)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ItemViewHolder -> holder.bind(getItem(position))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            ItemCastProfileBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }


    inner class ItemViewHolder(
        private val binding: ItemCastProfileBinding,
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(modelView: MovieCastModelView) {
            with(binding) {
                textViewNameCast.text = modelView.castName
                GlideUtils.getGlide(modelView.urlPhoto, itemView.context)
                    .into(imageViewPhotoCast)
            }
        }
    }
}