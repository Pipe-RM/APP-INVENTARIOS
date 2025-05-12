package com.example.proyecto11.fragmentos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.proyecto11.databinding.FragmentChatsBinding
import com.google.firebase.firestore.FirebaseFirestore

class FragmentChats : Fragment() {

    private var _binding: FragmentChatsBinding? = null
    private val binding get() = _binding!!

    private lateinit var db: FirebaseFirestore
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChatsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db = FirebaseFirestore.getInstance()

        adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, mutableListOf())
        binding.listaNotas.adapter = adapter

        binding.btnGuardar.setOnClickListener {
            val texto = binding.notaInput.text.toString().trim()
            if (texto.isNotEmpty()) {
                val nota = hashMapOf("texto" to texto)
                db.collection("notas").add(nota)
                    .addOnSuccessListener {
                        binding.notaInput.text.clear()
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(requireContext(), "Error al guardar: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
            }
        }

        db.collection("notas").addSnapshotListener { snapshots, _ ->
            val lista = mutableListOf<String>()
            snapshots?.forEach {
                lista.add(it.getString("texto") ?: "")
            }
            adapter.clear()
            adapter.addAll(lista)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Para evitar fugas de memoria
    }
}
