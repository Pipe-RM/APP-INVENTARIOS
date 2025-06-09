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
import com.example.proyecto11.CarritoConfirmar
import com.example.proyecto11.R
import com.example.proyecto11.databinding.ActivityAlimentosCategoriasBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class AlimentosCategorias : AppCompatActivity() {

    private lateinit var binding: ActivityAlimentosCategoriasBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAlimentosCategoriasBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNV2)

        // Opcional: marcar el ítem actual como seleccionado
        bottomNavigationView.selectedItemId = R.id.item_menu

        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.item_menu -> {
                    if (this !is AlimentosCategorias) { // ← Evita recargar si ya estás en Menu
                        startActivity(Intent(this, AlimentosCategorias::class.java))
                        overridePendingTransition(0, 0) // (opcional) sin animación
                    }
                    true
                }

                R.id.item_carrito -> {
                    val intent = Intent(this, CarritoConfirmar::class.java)
                    startActivity(intent)
                    true
                }

                else -> false
            }
        }
        aplicarInsets()
        configurarRecyclerSugerencias()
    }

    private fun irA(destino: Class<*>): Boolean {
        startActivity(Intent(this, destino))
        //overridePendingTransition(R.anim.slider_in_right, R.anim.slider_out_left)
        finish()
        return true
    }

    private fun aplicarInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun configurarRecyclerSugerencias() {
        val categorias =
            listOf("Desayunos", "Comidas", "Frios", "Ensaladas", "Postres", "Calientes")
        val todasLasComidas = categorias.flatMap { DataProvider.obtenerComidas(it) }

        val adapter = SugerenciasAdapter(todasLasComidas)
        binding.recyclerSugerencias.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerSugerencias.adapter = adapter
    }

    fun abrirAlimentosMenu(view: View) {
        startActivity(Intent(this, AlimentosMenu::class.java))
    }

    fun abrirBebidasCategorias(view: View) {
        startActivity(Intent(this, BebidasCategorias::class.java))
    }

    fun flechaHome(view: View) {
        val intent = Intent(this, com.example.proyecto11.MainActivity::class.java)
        intent.putExtra("fragment_destino", "home")
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()
    }
}
