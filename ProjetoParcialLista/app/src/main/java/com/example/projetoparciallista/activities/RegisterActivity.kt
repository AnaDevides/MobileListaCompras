package com.example.projetoparciallista.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.projetoparciallista.databinding.ActivityRegisterBinding
import com.example.projetoparciallista.models.User
import com.example.projetoparciallista.repository.UserRepository
import com.example.projetoparciallista.utils.ValidationUtils

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.registerButton.setOnClickListener {
            val name = binding.nameEditText.text.toString()
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            val confirmPassword = binding.confirmPasswordEditText.text.toString()

            if (ValidationUtils.isEmailValid(email) &&
                password == confirmPassword &&
                name.isNotEmpty()
            ) {
                val newUser = User(id = System.currentTimeMillis(), name = name, email = email, password = password)
                UserRepository.addUser(newUser)
                Toast.makeText(this, "Cadastro realizado!", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Erro no cadastro", Toast.LENGTH_SHORT).show()
            }
        }
    }

}