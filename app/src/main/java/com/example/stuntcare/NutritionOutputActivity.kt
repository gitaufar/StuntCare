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

class NutritionOutputActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nutrition_output)
        val auth = FirebaseAuth.getInstance()
        val database = Firebase.database
        val currentUser = auth.currentUser
        val ref = database.getReference(currentUser?.uid!!)
        val exit: ImageView = findViewById(R.id.exit)
        val embrace1: TextView = findViewById(R.id.embrace1)
        val embrace2: TextView = findViewById(R.id.embrace2)
        val embrace3: TextView = findViewById(R.id.embrace3)
        val avoid1: TextView = findViewById(R.id.avoid1)
        val avoid2: TextView = findViewById(R.id.avoid2)
        val avoid3: TextView = findViewById(R.id.avoid3)
        embrace1.text = "Folic Acid"
        embrace2.text = "Vitamin D"

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
                avoid1.text = "Caffeine"
                avoid2.text = "High-Mercury Fish"
                avoid3.text = "Excessive Vitamin A"
                embrace3.text = "Iron"

            } else if(period.months <= 6){
                avoid1.text = "Excessive Caffeine"
                avoid2.text = "High-Mercury Fish"
                avoid3.text = "Unpasteurized Dairy Products"
                embrace3.text = "Calsium"
            } else {
                avoid1.text = "Alcohol and Tobacco"
                avoid2.text = "Excessive Sugar and Junk Food"
                avoid3.text = "High Mercury Fish"
                embrace3.text = "Calsium & Iron"
            }
        }

        exit.setOnClickListener() {
            Intent(this, MainActivity2::class.java).also {
                startActivity(it)
            }
        }
    }
}