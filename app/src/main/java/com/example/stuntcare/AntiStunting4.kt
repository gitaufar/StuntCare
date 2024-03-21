package com.example.stuntcare

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.raon.R
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.database

class AntiStunting4 : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anti_stunting4)
        val database = Firebase.database
        val auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser
        val ref = database.getReference(currentUser?.uid!!)
        val etBeratBefore: TextView = findViewById(R.id.etBefore)
        val etBeratNow: TextView = findViewById(R.id.etNow)
        val illness1: TextView = findViewById(R.id.illnes1)
        val illness2: TextView = findViewById(R.id.illnes2)
        val illness3: TextView = findViewById(R.id.illnes3)
        val illness4: TextView = findViewById(R.id.illnes4)
        val alergyText: TextView = findViewById(R.id.alergy)
        val nodataIllness: TextView = findViewById(R.id.noDataIllnes)
        val nodataAlergy: TextView = findViewById(R.id.noDataAllergy)
        ref.child("History Of Illness").get().addOnSuccessListener {
            val ilnes = it.value.toString()
            if(ilnes != "") {
                try {
                    val ilnesSplit = ilnes.split("\n")
                    if (ilnesSplit.size < 3) {
                        illness1.text = ilnesSplit[0]
                        illness2.text = ilnesSplit[1]
                    } else if (ilnesSplit.size < 4) {
                        illness1.text = ilnesSplit[0]
                        illness2.text = ilnesSplit[1]
                        illness3.text = ilnesSplit[2]
                    } else {
                        illness1.text = ilnesSplit[0]
                        illness2.text = ilnesSplit[1]
                        illness3.text = ilnesSplit[2]
                        illness4.text = ilnesSplit[3]
                    }
                } catch (e: Exception) {
                    illness1.text = it.value.toString()
                }
            } else {
                nodataIllness.visibility = View.VISIBLE
            }
        }

        ref.child("pre-pregnancy weight").get().addOnSuccessListener {
            etBeratBefore.text = it.value.toString()
        }

        ref.child("post-pregnancy weight").get().addOnSuccessListener {
            etBeratNow.text = it.value.toString()
        }

        ref.child("Alergy").get().addOnSuccessListener {
            val alergy = it.value.toString()
            if(alergy != "") {
                try {
                    val alergySplit = alergy.split(" ")
                    if (alergySplit.size < 3) {
                        alergyText.text = "${alergySplit[0]},${alergySplit[1]}"
                    } else if (alergySplit.size < 4) {
                        alergyText.text = "${alergySplit[0]},${alergySplit[1]},${alergySplit[2]}"
                    } else {
                        alergyText.text = "${alergySplit[0]},${alergySplit[1]},${alergySplit[2]},${alergySplit[3]}"
                    }
                } catch (e: Exception) {
                    alergyText.text = it.value.toString()
                }
            } else {
                nodataAlergy.visibility = View.VISIBLE
            }
        }

    }
}