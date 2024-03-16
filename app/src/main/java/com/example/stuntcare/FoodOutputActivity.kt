package com.example.stuntcare

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.raon.R
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.database

private lateinit var firebaseAuth: FirebaseAuth
class FoodOutputActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_output)
        val auth = FirebaseAuth.getInstance()
        val database = Firebase.database
        val currentUser = auth.currentUser
        val ref = database.getReference(currentUser?.uid!!)

        val exit: ImageView = findViewById(R.id.exit)

        exit.setOnClickListener() {
            Intent(this, MainActivity2::class.java).also {
                startActivity(it)
            }
        }
    }
}