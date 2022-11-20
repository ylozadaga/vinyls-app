package com.example.vinyls.models

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
import com.example.vinyls.databinding.MusicianItemBinding
import com.example.vinyls.view.FragmentMusicianListDirections
import com.example.vinyls.models.Musician

class MusicianAdapter :RecyclerView.Adapter<MusicianAdapter.MusicianViewHolder>(){

    var musicians : List<Musician> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicianViewHolder {
        val withDataBinding: MusicianItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            MusicianViewHolder.LAYOUT,
            parent,
            false)
        return MusicianViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: MusicianViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.musician = musicians[position]
        }
        //*                         actionFragmentMusicianListToFragmentMusicianDetail
        holder.viewDataBinding.root.setOnClickListener {
            val action = FragmentMusicianListDirections.actionFragmentMusicianListToFragmentMusicianDetail(musicians[position].name,
                musicians[position].birthDate, musicians[position].image, musicians[position].description)
            holder.viewDataBinding.root.findNavController().navigate(action)
        }//
    }

    override fun getItemCount(): Int {
        return musicians.size
    }


    class MusicianViewHolder(val viewDataBinding: MusicianItemBinding) :
                RecyclerView.ViewHolder(viewDataBinding.root){
          companion object {
              @LayoutRes
              val LAYOUT = R.layout.musician_item
          }
        fun bind(musician: Musician) {
            Glide.with(itemView)
                .load(musician.image.toUri().buildUpon().scheme("https").build())
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.loading_animation)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .error(R.drawable.ic_broken_image))
                    .into(viewDataBinding.imageViewMusician)
        }
    }
}