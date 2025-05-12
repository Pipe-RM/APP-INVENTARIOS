package com.example.proyecto11

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.proyecto11.fragmentos.FragmentChats
import com.example.proyecto11.Fragmentos.FragmentHome
import com.example.proyecto11.Fragmentos.FragmentPerfil
import com.example.proyecto11.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        firebaseAuth = FirebaseAuth.getInstance()
        setContentView(binding.root)

        //Fragmento por defecto
        verFragmentoHome()

        binding.bottomNV.setOnItemSelectedListener { item ->
            when(item.itemId){
                R.id.item_perfil->{
                    //Visualizar el fragmento perfil
                    verFragmentoPerfil()
                    true
                }
                R.id.item_home->{
                    //Visualizar el fragmento home
                    verFragmentoHome()
                    true
                }
                R.id.item_chats->{
                    //Visualizar el fragmento chat
                    verFragmentoChats()
                    true
                }
                else->{
                    false
                }
            }
        }

        if(firebaseAuth.currentUser == null){
            irOpcionesLogin()
        }

    }

    private fun verFragmentoPerfil(){
        binding.tvTitulo.text = "Perfil"

        val fragment = FragmentPerfil()
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(binding.fragmentoFL.id, fragment, "Fragment Perfil")
        fragmentTransaction.commit()
    }

    private fun verFragmentoHome(){
        binding.tvTitulo.text = "Principal"

        val fragment = FragmentHome()
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(binding.fragmentoFL.id, fragment, "Fragment Home")
        fragmentTransaction.commit()
    }

    private fun verFragmentoChats(){
        binding.tvTitulo.text = "Chats"

        val fragment = FragmentChats()
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(binding.fragmentoFL.id, fragment, "Fragment Chat")
        fragmentTransaction.commit()
    }

    private fun irOpcionesLogin() {
        startActivity(Intent(applicationContext, OpcionesLoginActivity::class.java))
        finishAffinity()
    }

}
