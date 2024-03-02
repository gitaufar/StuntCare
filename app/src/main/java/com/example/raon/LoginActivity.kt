package com.example.raon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    val auth = Firebase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loginn)
        val auth = FirebaseAuth.getInstance()
        val btnLogin: Button = findViewById(R.id.login)
        
    }
}