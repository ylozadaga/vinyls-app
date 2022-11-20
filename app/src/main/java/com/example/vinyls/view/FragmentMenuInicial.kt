package com.example.vinyls.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.vinyls.R


class FragmentMenuInicial : Fragment(R.layout.fragment_menu_inicial) {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_menu_inicial, container, false);
        val btn: Button = view.findViewById(R.id.btnVisitante)

        val btn2: Button = view.findViewById(R.id.btnColeccionista)

        btn.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentMenuInicial_to_fragmentMenuVisitante)
        }

        btn2.setOnClickListener {
            val fragment = FragmentMenuColeccionista()
            findNavController().navigate(R.id.action_fragmentMenuInicial_to_fragmentMenuColeccionista)
        }
        /*btn2.setOnClickListener{
            val user = 2
            val bundle = Bundle()
            bundle.putInt("user", user)

            val fragment = FragmentAlbumList()
            //val fragment = FragmentMenuColeccionista()
            fragment.arguments = bundle
            val action = FragmentMenuInicialDirections.actionFragmentMenuInicialToFragmentAlbumList(2)
            view.findNavController().navigate(action)

        }*/


        return view
    }


}