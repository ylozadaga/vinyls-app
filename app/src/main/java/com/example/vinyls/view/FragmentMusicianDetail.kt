package com.example.vinyls.view

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.navArgs
import com.example.vinyls.R
import com.example.vinyls.databinding.FragmentAlbumDetailBinding
import com.example.vinyls.databinding.FragmentMusicianDetailBinding
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_album_detail.*

class FragmentMusicianDetail : Fragment() {
    private var _binding: FragmentMusicianDetailBinding? = null
    private val binding get() = _binding!!

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMusicianDetailBinding.inflate(inflater, container, false)
        val args: FragmentMusicianDetailArgs by navArgs()
        val view = inflater.inflate(R.layout.fragment_musician_detail, container, false);

        binding.tvName.text= args.name
        binding.tvBirthDate.text=args.birthDate
        val imgUrl : String? = args.image
        Picasso.get().load(imgUrl).into(binding.ivImage);
        binding.tvDescription.text=args.description
        return binding.root
    }
}