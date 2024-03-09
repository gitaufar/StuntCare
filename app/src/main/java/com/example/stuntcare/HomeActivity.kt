package com.example.raon

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
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
        ref.child("nama").get().addOnSuccessListener {
            text.text = "Good Morning ${it.value}"
        }.addOnFailureListener{
            Log.e("firebase", "Error getting data", it)
        }



//
//        val user = object{
//            val data = 1
//            val alamat = "bogor"
//        }
//
//        ref.child("coba").setValue(user)
//
//        myRef.setValue("Hello, World!")
//
//        val firebaseAuth = FirebaseAuth.getInstance()
//
//        currentUser?.displayName
//
//        val newReference = database.getReference(currentUser?.uid!!)
//
//        val orang = object{
//            val nama = "aufar"
//            val umur = 17
//        }
//
//        newReference.setValue(orang)
////        newReference.child("users").get().addOnSuccessListener


        btnLogout.setOnClickListener(){
            auth.signOut()
            Intent(this, MainActivity::class.java).also{
                startActivity(it)
            }
        }
    }
}