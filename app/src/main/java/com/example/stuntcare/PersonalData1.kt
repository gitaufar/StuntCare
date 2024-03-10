package com.example.stuntcare

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.example.raon.HomeActivity
import com.example.raon.R
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.database

class PersonalData1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personal_data1)
        val database = Firebase.database
        val auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser
        val ref = database.getReference(currentUser?.uid!!)
        val etNama: EditText = findViewById(R.id.editTextText4)
        val btnConfirm: AppCompatButton = findViewById(R.id.btn_confirm)

        btnConfirm.setOnClickListener() {
            if (etNama.text.toString() != "") {
                val data = object {
                    var nama = etNama.text.toString()
                }
                ref.setValue(data)
                Intent(this, HomeActivity::class.java).also{
                    startActivity(it)
                }
            } else {
                Toast.makeText(this, "name cannot be empty", Toast.LENGTH_SHORT).show()
            }
        }
    }
}