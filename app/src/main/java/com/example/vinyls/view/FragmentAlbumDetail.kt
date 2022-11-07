package com.example.vinyls.view

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.vinyls.R
import com.example.vinyls.databinding.FragmentAlbumDetailBinding
import com.squareup.picasso.Picasso

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FragmentAlbumDetail : Fragment() {

    private var _binding: FragmentAlbumDetailBinding? = null
    private val binding get() = _binding!!

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAlbumDetailBinding.inflate(inflater, container, false)
        val args: FragmentAlbumDetailArgs by navArgs()
        val view = inflater.inflate(R.layout.activity_album_detail, container, false);
        val tvName : TextView = view.findViewById(R.id.tvName)
        val tvGenre : TextView = view.findViewById(R.id.tvGenre)
        val ivCover : ImageView = view.findViewById(R.id.ivCover)
        val tvFecha : TextView = view.findViewById(R.id.tvFecha)
        val tvDescription : TextView = view.findViewById(R.id.tvDescription)



        tvName.text = args.name
        tvGenre.text = args.genero
        tvFecha.text = args.fecha
        tvDescription.text = args.description
        val imgUrl : String? = args.cover
        Picasso.get().load(imgUrl).into(ivCover);
        return view
    }


}