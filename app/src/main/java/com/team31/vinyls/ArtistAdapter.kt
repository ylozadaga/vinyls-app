package com.team31.vinyls

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter

class ArtistAdapter(private val mContext: Context, private val listArtist: List<Artist>) : ArrayAdapter<Artist> (mContext,0,listArtist) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layout = LayoutInflater.from(mContext).inflate(R.layout.item_artist, parent, false)

        val artist = listArtist[position]

        layout.textViewArtistName.text = artist.name
        layout.
        

        return layout
    }
}