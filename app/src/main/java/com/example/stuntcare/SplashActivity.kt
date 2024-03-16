package com.example.stuntcare

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.raon.MainActivity
import com.example.raon.R
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.database

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val firebaseAuth = FirebaseAuth.getInstance()
        val database = Firebase.database

        Handler(Looper.getMainLooper()).postDelayed({

                if (firebaseAuth.currentUser != null) {
                    val currentUser = firebaseAuth.currentUser
                    val ref = database.getReference(currentUser?.uid!!)
                    ref.child("nama").get().addOnSuccessListener {
                        if(it.exists()) {
                            val intent = Intent(this, MainActivity2::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            val intent = Intent(this, PersonalData1::class.java)
                            startActivity(intent)
                            finish()
                        }
                    }.addOnFailureListener{
                        Log.e("firebase", "Error getting data", it)
                    }
                } else {
                    Intent(this, MainActivity::class.java).also {
                        startActivity(it)
                        finish()
                    }
                }
        },2000L)

    }
}