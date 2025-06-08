package com.example.proyecto11.MCategoriasAlimentos

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.proyecto11.R
import com.example.proyecto11.ScrollAlimentos

class BebidasCategorias : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_bebidas_categorias)




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun abrirMenuFrios(view: View){
        val intent = Intent(this, ScrollAlimentos::class.java)
        intent.putExtra("tv_titulo_menu", "Frios")
        startActivity(intent)
    }

    fun abrirMenuCalientes(view: View){
        val intent = Intent(this, ScrollAlimentos::class.java)
        intent.putExtra("tv_titulo_menu", "Calientes")
        startActivity(intent)
    }
}