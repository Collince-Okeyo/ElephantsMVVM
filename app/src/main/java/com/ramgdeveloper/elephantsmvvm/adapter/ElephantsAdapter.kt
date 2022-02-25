package com.ramgdeveloper.elephantsmvvm.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ramgdeveloper.elephantsmvvm.databinding.ElephantsRowBinding
import com.ramgdeveloper.elephantsmvvm.model.Elephants

class ElephantsAdapter : ListAdapter<Elephants, ElephantsAdapter.ElephantsViewHolder>(ElephantsDiffUtil) {

    object ElephantsDiffUtil : DiffUtil.ItemCallback<Elephants>() {
        override fun areItemsTheSame(oldItem: Elephants, newItem: Elephants, ): Boolean {
            return oldItem == newItem
        }
        override fun areContentsTheSame(oldItem: Elephants, newItem: Elephants, ): Boolean {
            return oldItem.index == newItem.index
        }
    }
    inner class ElephantsViewHolder(private val binding: ElephantsRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(elephants: Elephants?) {

            Glide.with(binding.elephantImage)
                .load(elephants?.image)
                .centerCrop()
                .into(binding.elephantImage)

            binding.elephantName.text = elephants?.name
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ElephantsViewHolder {
        return ElephantsViewHolder(ElephantsRowBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))
    }
    override fun onBindViewHolder(holder: ElephantsViewHolder, position: Int) {
        val elephant = getItem(position)
        holder.bind(elephant)
    }
}