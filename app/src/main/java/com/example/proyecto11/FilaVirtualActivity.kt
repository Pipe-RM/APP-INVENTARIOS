package com.example.proyecto11

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.proyecto11.databinding.ActivityFilaVirtualBinding

class FilaVirtualActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFilaVirtualBinding
    private val listaPersonas = mutableListOf<String>()
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFilaVirtualBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listaPersonas)
        binding.listViewPersonas.adapter = adapter

        binding.btnAgregar.setOnClickListener {
            val nombre = binding.etNombre.text.toString().trim()
            if (nombre.isNotEmpty() && !listaPersonas.contains(nombre)) {
                listaPersonas.add(nombre)
                adapter.notifyDataSetChanged()
                binding.etNombre.text.clear()
            }
        }

        binding.btnQuitar.setOnClickListener {
            val nombre = binding.etNombre.text.toString().trim()
            listaPersonas.remove(nombre)
            adapter.notifyDataSetChanged()
            binding.etNombre.text.clear()
        }
    }
}
