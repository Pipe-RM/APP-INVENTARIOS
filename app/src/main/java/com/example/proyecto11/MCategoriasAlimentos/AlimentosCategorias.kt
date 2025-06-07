package com.example.proyecto11.MCategoriasAlimentos

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyecto11.Adapter.DataProvider
import com.example.proyecto11.Adapter.SugerenciasAdapter
import com.example.proyecto11.databinding.ActivityAlimentosCategoriasBinding

class AlimentosCategorias : AppCompatActivity() {

    private lateinit var binding: ActivityAlimentosCategoriasBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Inflar el layout con binding
        binding = ActivityAlimentosCategoriasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Nota para mi: Son Insets para la compatibilidad con gestos
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Obtención de las comidas de todas las categorías
        val todasLasComidas = mutableListOf<com.example.proyecto11.Adapter.Comida>()
        val categorias = listOf("Desayunos", "Comidas", "Ensaladas", "Postres")
        for (cat in categorias) {
            todasLasComidas.addAll(DataProvider.obtenerComidas(cat))
        }

        // Configuración del RecyclerView horizontal con binding
        val adapter = SugerenciasAdapter(todasLasComidas)
        binding.recyclerSugerencias.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerSugerencias.adapter = adapter
        }

 //FUNCIONES PARA ABRIR LOS MENUS
    fun abrirAlimentosMenu(view: View) {
        startActivity(Intent(this, AlimentosMenu::class.java))
    }

    fun abrirBebidasCategorias(view: View) {
        startActivity(Intent(this, BebidasCategorias::class.java))
    }
}
