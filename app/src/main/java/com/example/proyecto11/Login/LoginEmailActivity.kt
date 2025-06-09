package com.example.proyecto11.Login

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.proyecto11.MainActivity
import com.example.proyecto11.databinding.ActivityLoginEmailBinding
import com.google.firebase.auth.FirebaseAuth

class LoginEmailActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginEmailBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginEmailBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Espere porfavor")
        progressDialog.setCanceledOnTouchOutside(false)

        binding.btnIngresar.setOnClickListener{
            validarInformacion()
        }

        binding.tvRegistrarme.setOnClickListener{
            startActivity(Intent(applicationContext, RegistroEmailActivity::class.java))

        }
    }


    private var email = ""
    private var password = ""
    private fun validarInformacion() {
        email = binding.etEmail.text.toString().trim()
        password = binding.etPassword.text.toString().trim()

        if(email.isEmpty()){
            binding.etEmail.error = "Ingrese email"
            binding.etEmail.requestFocus()
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.etEmail.error = "Email no valido"
            binding.etEmail.requestFocus()
        }
        else if(password.isEmpty()){
            binding.etPassword.error = "Ingrese la contraseña"
            binding.etPassword.requestFocus()
        }
        else {
            logearUsuario()
        }
    }

    private fun logearUsuario() {
        progressDialog.setMessage("Ingresando")
        progressDialog.show()

        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                progressDialog.dismiss()
                startActivity(Intent(this, MainActivity::class.java))
                finishAffinity()
            }
            .addOnFailureListener{e->
                Toast.makeText(
                    this,
                    "No se logeo correctamente debido a ${e.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }
}