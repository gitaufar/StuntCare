package com.example.stuntcare

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.raon.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.database

class AntiStunting2 : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_anti_stunting2)
        val auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser
        val db = Firebase.database
        val ref = db.getReference(currentUser?.uid!!)
        val botnav: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        val flEmpty: FrameLayout = findViewById(R.id.flEmpty)
        val flExist: FrameLayout = findViewById(R.id.flExist)

        ref.child("lastPeriod").get().addOnSuccessListener {
            if (it.exists()){
                flEmpty.visibility = View.GONE
                flExist.visibility = View.VISIBLE
            } else {
                flEmpty.visibility = View.VISIBLE
                flExist.visibility = View.GONE
            }
        }

        botnav.setSelectedItemId(R.id.place_holder)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val btnInput: AppCompatButton = findViewById(R.id.button4)
        btnInput.setOnClickListener(){
            Intent(this,AntiStunting2_1::class.java).also{
                startActivity(it)
            }
        }
    }
}