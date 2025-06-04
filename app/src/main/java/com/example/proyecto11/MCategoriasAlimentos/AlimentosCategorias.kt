package com.example.proyecto11.MCategoriasAlimentos

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.proyecto11.R

class AlimentosCategorias : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_alimentos_categorias)

        // Asegúrate de que el layout tenga un View con id="@+id/main"
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun abrirAlimentosMenu(view: View) {
        startActivity(Intent(this, AlimentosMenu::class.java))
    }

    fun abrirBebidasCategorias(view: View) {
        startActivity(Intent(this, BebidasCategorias::class.java))
    }
}
