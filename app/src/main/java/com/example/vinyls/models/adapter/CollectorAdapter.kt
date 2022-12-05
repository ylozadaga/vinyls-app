package com.example.vinyls.models.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.vinyls.R
import com.example.vinyls.databinding.CollectorItemBinding
import com.example.vinyls.models.Album
import com.example.vinyls.models.Collector
import com.example.vinyls.view.FragmentAlbumListDirections
import com.example.vinyls.view.FragmentCollectorListDirections


class CollectorAdapter : RecyclerView.Adapter<CollectorAdapter.CollectorViewHolder>(){

    var collectors : List<Collector> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectorViewHolder {
        val withDataBinding: CollectorItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            CollectorViewHolder.LAYOUT,
            parent,
            false)
        return CollectorViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: CollectorViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.collector = collectors[position]
        }

        holder.viewDataBinding.root.setOnClickListener {
            val action = FragmentCollectorListDirections.actionFragmentCollectorListToFragmentCollectorDetail(collectors[position].name,
                collectors[position].email, collectors[position].telephone)
            // Navigate using that action
            holder.viewDataBinding.root.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return collectors.size
    }

    class CollectorViewHolder(val viewDataBinding: CollectorItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root){
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.collector_item
        }

    }
}