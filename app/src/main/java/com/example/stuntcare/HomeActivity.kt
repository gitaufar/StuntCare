package com.example.raon

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class HomeActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val auth = FirebaseAuth.getInstance()
        val btnLogout: Button = findViewById(R.id.logout)

        btnLogout.setOnClickListener(){
            auth.signOut()
            Intent(this, MainActivity::class.java).also{
                startActivity(it)
            }
        }
    }
}