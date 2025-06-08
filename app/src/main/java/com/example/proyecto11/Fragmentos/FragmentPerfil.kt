package com.example.proyecto11.fragmentos

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.proyecto11.Login.OpcionesLoginActivity
import com.example.proyecto11.databinding.FragmentPerfilBinding
import com.google.firebase.auth.FirebaseAuth

class FragmentPerfil : Fragment() {

    private lateinit var binding: FragmentPerfilBinding
    private lateinit var mContext : Context
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onAttach(context: Context) {
        mContext = context
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentPerfilBinding.inflate(layoutInflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firebaseAuth = FirebaseAuth.getInstance()
        val usuario = firebaseAuth.currentUser

        // Mostrar el nombre en el TextView
        usuario?.let {
            val nombre = it.displayName ?: "Usuario sin nombre"
            binding.tvNombre.text = nombre

            val correo = it.email ?: "Sin correo"
            binding.tvInfo.text = "Correo: $correo"
        }

        // Botón cerrar sesión
        binding.btnCerrarsesion.setOnClickListener {
            firebaseAuth.signOut()
            startActivity(Intent(mContext, OpcionesLoginActivity::class.java))
            activity?.finishAffinity()
        }
    }


}