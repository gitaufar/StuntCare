package com.example.raon


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val login: AppCompatButton = findViewById(R.id.btnLogin)
        val register: AppCompatButton = findViewById(R.id.btnRegister)

        login.setOnClickListener(){
            Intent(this,LoginActivity::class.java).also{
                startActivity(it)
            }
        }

        register.setOnClickListener(){
            Intent(this,RegisterActivity::class.java).also{
                startActivity(it)
            }
        }
    }

    override fun onStart() {

        firebaseAuth = FirebaseAuth.getInstance()
        super.onStart()
        if (firebaseAuth.currentUser != null) {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}