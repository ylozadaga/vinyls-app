package com.example.vinyls.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vinyls.R
import com.example.vinyls.databinding.FragmentMusicianListBinding
import com.example.vinyls.models.Album
import com.example.vinyls.models.Musician
import com.example.vinyls.models.MusicianAdapter
import com.example.vinyls.viewmodels.AlbumListViewModel
import com.example.vinyls.viewmodels.MusicianListViewModel

@Suppress("DEPRECATION")
class FragmentMusicianList : Fragment() {

    private var _binding: FragmentMusicianListBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: MusicianListViewModel
    private var viewModelAdapter: MusicianAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMusicianListBinding.inflate(inflater, container, false)
        val view = binding.root
        viewModelAdapter = MusicianAdapter()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = binding.musicianRv
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = viewModelAdapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        viewModel = ViewModelProvider(this, MusicianListViewModel.Factory(activity.application)).get(
            MusicianListViewModel::class.java)
        viewModel.musicians.observe(viewLifecycleOwner, Observer<List<Musician>> {
            it.apply {
                viewModelAdapter!!.musicians = this
            }
        })
        viewModel.eventNetworkError.observe(viewLifecycleOwner, Observer<Boolean> { isNetworkError ->
            if (isNetworkError) onNetworkError()
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding= null
    }

    private fun onNetworkError() {
        if(!viewModel.isNetworkErrorShown.value!!) {
            Toast.makeText(activity, "Network Error", Toast.LENGTH_LONG).show()
            viewModel.onNetworkErrorShown()
        }
    }
}