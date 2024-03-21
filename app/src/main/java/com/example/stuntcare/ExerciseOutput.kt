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

class ExerciseOutput : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise_output)
        val auth = FirebaseAuth.getInstance()
        val database = Firebase.database
        val currentUser = auth.currentUser
        val ref = database.getReference(currentUser?.uid!!)
        val exit: ImageView = findViewById(R.id.exit)
        val embrace1: TextView = findViewById(R.id.embrace1)
        val embrace2: TextView = findViewById(R.id.embrace2)
        val embrace3: TextView = findViewById(R.id.embrace3)
        val embrace4: TextView = findViewById(R.id.embrace4)
        val embrace5: TextView = findViewById(R.id.embrace5)

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
                embrace1.text = "-Swimming"
                embrace2.text = "-Stationary Cycling"
                embrace3.text = "-Stretching and Breathing Exercises"
                embrace4.text = "-Prenatal Yoga"
                embrace5.text = ""

            } else if(period.months <= 6){
                embrace1.text = "-Swimming"
                embrace2.text = "-Low-Impact Aerobics"
                embrace3.text = "-Strength Training with Body Weight or Light Weights"
                embrace4.text = "-Prenatal Exercise"
                embrace5.text = "-Pelvic Floor Exercises"
            } else {
                embrace1.text = "-Prenatal Swimming"
                embrace2.text = "-Prenatal Pilates"
                embrace3.text = "-Gentle Prenatal Yoga"
                embrace4.text = "-Pelvic Floor Exercises"
                embrace5.text = "-Walking"
            }
        }

        exit.setOnClickListener(){
            Intent(this,MainActivity2::class.java).also{
                startActivity(it)
            }
        }
    }
}