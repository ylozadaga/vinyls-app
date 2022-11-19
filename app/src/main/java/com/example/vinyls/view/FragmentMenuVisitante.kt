package com.example.vinyls.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.vinyls.R

class FragmentMenuVisitante : Fragment(R.layout.fragment_menu_visitante) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonAlbums = requireView().findViewById<Button>(R.id.buttonAlbumsGuest)
        val buttonArtists = requireView().findViewById<Button>(R.id.buttonArtistGuest)
        val buttonCollector = requireView().findViewById<Button>(R.id.buttonCollectorsGuest)

        buttonAlbums.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentMenuVisitante_to_fragmentAlbumList)
        }

        buttonArtists.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentMenuVisitante_to_fragmentMusicianList2)
        }
        buttonCollector.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentMenuVisitante_to_fragmentCollectorList3)
        }
    }

}