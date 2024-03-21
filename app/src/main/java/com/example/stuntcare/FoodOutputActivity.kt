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

private lateinit var firebaseAuth: FirebaseAuth
class FoodOutputActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_output)
        val auth = FirebaseAuth.getInstance()
        val database = Firebase.database
        val currentUser = auth.currentUser
        val ref = database.getReference(currentUser?.uid!!)
        val embrace1: TextView = findViewById(R.id.embrace1)
        val embrace2: TextView = findViewById(R.id.embrace2)
        val embrace3: TextView = findViewById(R.id.embrace3)
        val embrace4: TextView = findViewById(R.id.embrace4)
        val embrace5: TextView = findViewById(R.id.embrace5)
        val avoid1: TextView = findViewById(R.id.avoid1)
        val avoid2: TextView = findViewById(R.id.avoid2)
        val avoid3: TextView = findViewById(R.id.avoid3)
        val avoid4: TextView = findViewById(R.id.avoid4)
        val avoid5: TextView = findViewById(R.id.avoid5)
        val avoid6: TextView = findViewById(R.id.avoid6)

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
                avoid1.text = "High-mercury fish"
                avoid2.text = "Raw or undercooked meat and eggs"
                avoid3.text = "Unpasteurized dairy products"
                avoid4.text = "Deli meats and processed meats"
                avoid5.text = "Raw sprouts"
                avoid6.text = "Excessive caffeine"
                embrace1.text = "Folate-rich foods(spinach, kale, lentils, etc)"
                embrace2.text = "Lean protein(fish, lean meats, eggs, tofu,etc)"
                embrace3.text = "Whole grains(brown rice, quinoa, oats, etc)"
                embrace4.text = "Calcium-rich foods(milk, yogurt, cheese, etc)"
                embrace5.text = "Iron-rich foods(lean meats, poultry, fish, etc)"

            } else if(period.months <= 6){
                avoid2.text = "High-mercury fish"
                avoid3.text = "Raw or undercooked meat and eggs"
                avoid4.text = "Unpasteurized dairy products"
                avoid5.text = "Alcohol"
                avoid6.text = "Soft cheeses"
                embrace1.text = "Lean protein(fish, lean meats, eggs, tofu,etc)"
                embrace2.text = "Omega-3 fatty acids(salmon, trout, sardines, etc)"
                embrace3.text = "Folate-rich foods(spinach, kale, lentils, etc)"
                embrace4.text = "Calcium-rich foods(Milk,Yogurt,Cheese,etc)"
                embrace5.text = "Fiber-rich foods(fruits, vegetables, whole grains, etc)"
            } else {
                avoid2.text = "Raw or undercooked meat and eggs"
                avoid3.text = "High-mercury fish"
                avoid4.text = "Unpasteurized dairy products"
                avoid5.text = "Caffeine"
                avoid6.text = "Alkohol"
                embrace1.text = "Small, frequent meals. Such as crackers, toast, bananas, and rice"
                embrace2.text = "Calcium-rich foods(Milk,Yogurt,Cheese,etc)"
                embrace3.text = "Folate-rich foods(spinach, kale, lentils, etc)"
                embrace4.text = "Iron-rich foods(lean meats, poultry, fish, etc)"
                embrace5.text = "Lean protein(fish, lean meats, eggs, tofu,etc)"
            }
        }

        val exit: ImageView = findViewById(R.id.exit)

        exit.setOnClickListener() {
            Intent(this, MainActivity2::class.java).also {
                startActivity(it)
            }
        }
    }
}