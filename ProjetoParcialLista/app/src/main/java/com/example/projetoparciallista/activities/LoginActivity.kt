package com.example.projetoparciallista.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.projetoparciallista.MainActivity
import com.example.projetoparciallista.controller.AuthController
import com.example.projetoparciallista.databinding.ActivityLoginBinding
import com.example.projetoparciallista.utils.ValidationUtils

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginButton.setOnClickListener {
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            if (ValidationUtils.isEmailValid(email) && ValidationUtils.isPasswordValid(password)) {
                val user = AuthController.login(email, password)
                if (user != null) {
                    // Navegar para a tela de listas
                    startActivity(Intent(this, ShoppingListActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this, "Credenciais inválidas", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Email ou senha inválidos", Toast.LENGTH_SHORT).show()
            }
        }

        binding.createAccountTextView.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

}