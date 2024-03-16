package com.example.raon

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.stuntcare.AntiStunting2
import com.example.stuntcare.ProfilActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.database

class HomeActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val database = Firebase.database


        val auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser
        val ref = database.getReference(currentUser?.uid!!)
        val btnLogout: Button = findViewById(R.id.logout)
        val text: TextView = findViewById(R.id.text)

        val fab: FloatingActionButton = findViewById(R.id.fab)

        fab.setOnClickListener(){
            Intent(this, AntiStunting2::class.java).also{
                startActivity(it)
            }
        }

        ref.child("nama").get().addOnSuccessListener {
            text.text = "Good Morning ${it.value}"
        }.addOnFailureListener {
            Log.e("firebase", "Error getting data", it)
        }


        btnLogout.setOnClickListener() {
            auth.signOut()
            Intent(this, MainActivity::class.java).also {
                startActivity(it)
            }
        }

        val botnav: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        botnav.setSelectedItemId(R.id.bottom_home)

        botnav.setOnItemSelectedListener { it ->
            when (it.itemId) {
                R.id.bottom_profile -> {
                    Intent(this, ProfilActivity::class.java).also {
                        startActivity(it)
                        finish()
                    }
                    true
                }

                R.id.bottom_procare -> {
                    Intent(this, HomeActivity::class.java).also {
                        startActivity(it)
                        finish()
                    }
                    true
                }

                R.id.bottom_stream -> {
                    Intent(this, HomeActivity::class.java).also {
                        startActivity(it)
                        finish()
                    }
                    true
                }
                else -> false
            }

        }
    }
}