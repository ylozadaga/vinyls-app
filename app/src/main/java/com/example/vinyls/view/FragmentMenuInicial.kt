package com.example.vinyls.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import com.example.vinyls.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentMenuInicial.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentMenuInicial : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_menu_inicial, container, false);
        val btn : TextView = view.findViewById(R.id.tvVisitante)

        val btn2 : TextView = view.findViewById(R.id.tvColeccionista)
        btn2.setOnClickListener{
            val user = 2
            val bundle = Bundle()
            bundle.putInt("user", user)

            val fragment = FragmentAlbumList()
            fragment.arguments = bundle
            val action = FragmentMenuInicialDirections.actionFragmentMenuInicialToFragmentAlbumList(2)
            view.findNavController().navigate(action)

        }


        return view
    }



}