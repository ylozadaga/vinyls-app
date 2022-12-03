package com.example.vinyls.view

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.navArgs
import com.example.vinyls.R
import com.example.vinyls.databinding.FragmentCollectorDetailBinding
import kotlinx.android.synthetic.main.fragment_album_detail.*

class FragmentCollectorDetail : Fragment() {
    private var _binding: FragmentCollectorDetailBinding? = null
    private val binding get() = _binding!!

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCollectorDetailBinding.inflate(inflater, container, false)
        val args: FragmentCollectorDetailArgs by navArgs()
        val view = inflater.inflate(R.layout.fragment_collector_detail, container, false);
        val tvNameCollector: TextView = view.findViewById(R.id.tvNameCollector)
        val tvTelephone: TextView = view.findViewById(R.id.tvTelephone)
        val tvEmail: TextView = view.findViewById(R.id.tvEmailCollector)

        tvNameCollector.text = args.name
        tvTelephone.text = args.telephone
        tvEmail.text = args.email

        return view
    }
}