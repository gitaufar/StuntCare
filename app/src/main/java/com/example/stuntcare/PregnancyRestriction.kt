package com.example.stuntcare

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.raon.R
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.database
import java.time.LocalDate
import java.time.Period

class PregnancyRestriction : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pregnancy_restriction)
        val auth = FirebaseAuth.getInstance()
        val database = Firebase.database
        val currentUser = auth.currentUser
        val ref = database.getReference(currentUser?.uid!!)
        val exit: ImageView = findViewById(R.id.exit)
        val avoid1: TextView = findViewById(R.id.avoid1)
        val avoid2: TextView = findViewById(R.id.avoid2)
        val avoid3: TextView = findViewById(R.id.avoid3)
        val avoid4: TextView = findViewById(R.id.avoid4)
        val avoid5: TextView = findViewById(R.id.avoid5)


        ref.child("lastPeriod").get().addOnSuccessListener {
            val tanggal = it.value.toString()
            val tanggalSplit = tanggal.split("/")
            val tanggalInt = IntArray(tanggalSplit.size)
            for(i in tanggalSplit.indices){
                tanggalInt[i] = tanggalSplit[i].toInt()
            }
            val pastDate = LocalDate.of(tanggalInt[2], tanggalInt[1], tanggalInt[0])
            val currentDate = LocalDate.now()
            val period = Period.between(pastDate, currentDate)
            if(period.months <= 3){
                avoid1.text = "Raw or undercooked foods"
                avoid2.text = "Consuming alcohol and cigarettes"
                avoid3.text = "Consuming excessive caffeine"
                avoid4.text = "Consume medicine without consulting with a doctor"
                avoid5.text = ""

            } else if(period.months <= 6){
                avoid1.text = "Food heated in the microwave in a plastic container"
                avoid2.text = "Raw or undercooked foods"
                avoid3.text = "Consuming alcohol and cigarettes"
                avoid4.text = "Consuming excessive caffeine"
                avoid5.text = ""
            } else {
                avoid1.text = "Consuming caffeine over 200mg/day"
                avoid2.text = "Seafood high in mercury"
                avoid3.text = "Raw or undercooked foods"
                avoid4.text = "Herbal supplements or medicines without a doctor\'s approval"
                avoid5.text = "Foods or drinks that are high in added sugar"
            }
        }

        exit.setOnClickListener() {
            Intent(this, MainActivity2::class.java).also {
                startActivity(it)
            }
        }
    }
}