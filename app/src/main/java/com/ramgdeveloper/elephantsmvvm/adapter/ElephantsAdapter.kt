package com.ramgdeveloper.elephantsmvvm.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ramgdeveloper.elephantsmvvm.databinding.ElephantsRowBinding
import com.ramgdeveloper.elephantsmvvm.model.Elephant

class ElephantsAdapter(private val onClickListener: View.OnClickListener) :
    ListAdapter<Elephant.ElephantItem, ElephantsAdapter.ElephantsViewHolder>(ElephantsDiffUtil) {

    object ElephantsDiffUtil : DiffUtil.ItemCallback<Elephant.ElephantItem>() {
        override fun areItemsTheSame(
            oldItem: Elephant.ElephantItem,
            newItem: Elephant.ElephantItem,
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: Elephant.ElephantItem,
            newItem: Elephant.ElephantItem,
        ): Boolean {
            return oldItem.index == newItem.index
        }
    }

    inner class ElephantsViewHolder(private val binding: ElephantsRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(elephant: Elephant.ElephantItem?) {

            Glide.with(binding.elephantImage)
                .load(elephant?.image)
                .into(binding.elephantImage)

            binding.elephantName.text = elephant?.name
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

        holder.itemView.setOnClickListener {
            onClickListener.onClick(it)
        }
    }
}