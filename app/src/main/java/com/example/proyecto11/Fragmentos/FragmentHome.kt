package com.example.proyecto11.fragmentos

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.proyecto11.FilaVirtualActivity
import com.example.proyecto11.MCategoriasAlimentos.AlimentosCategorias
import com.example.proyecto11.R

class FragmentHome : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // Referencias a las imágenes
        val imageView = view.findViewById<ImageView>(R.id.IVcomal)
        val secondImageView = view.findViewById<ImageView>(R.id.IVbus)

        // Click en IVcomal -> AlimentosCategorias
        imageView.setOnClickListener {
            val intent = Intent(requireContext(), AlimentosCategorias::class.java)
            startActivity(intent)
        }

        // Click en IVbus -> FilaVirtualActivity
        secondImageView.setOnClickListener {
            val intent = Intent(requireContext(), FilaVirtualActivity::class.java)
            startActivity(intent)
        }

        return view
    }
}