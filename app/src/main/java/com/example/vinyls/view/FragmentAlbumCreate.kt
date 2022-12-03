package com.example.vinyls.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.vinyls.R
import com.example.vinyls.databinding.FragmentAlbumCreateBinding
import com.example.vinyls.databinding.FragmentAlbumListBinding
import com.example.vinyls.models.adapter.AlbumsAdapter

class FragmentAlbumCreate : Fragment() {
    private var _binding: FragmentAlbumCreateBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAlbumCreateBinding.inflate(inflater, container, false)
        val view = binding.root
        //viewModelAdapter = AlbumsAdapter()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createButton()
    }

    private fun createButton() {
        binding.btnSendAlbum.setOnClickListener {
            sendDataToServer()
        }
    }

    private fun sendDataToServer() {
        var strAlbum = "{\n \"name\": \"" +
                binding.etNameCreateAlbum.text.toString() +
                "\",\n  \"cover\":\"" +
                binding.etCoverCreateAlbum.text.toString() +
                "\",\n  \"releaseDate\": \"" +
                binding.etReleaseDateCreateAlbum.text.toString() +
                "\",\n  \"description\": \"" +
                binding.etDescriptionCreateAlbum.text.toString() +
                "\",\n  \"genre\": \"" +
                getGenre() +
                "\",\n  \"recordLabel\": \"" +
                binding.etRecordLabelCreateAlbum.text.toString() +
                "\"\n}"
        Log.i("data Captured",strAlbum)

    }

    private fun getGenre(): String {
        with(binding){
            return when(tbGenre.checkedButtonId){
                R.id.btnSalsa -> btnSalsa.text.toString()
                R.id.btnRock -> btnRock.text.toString()
                else -> btnFolk.text.toString()
            }
        }

    }
}