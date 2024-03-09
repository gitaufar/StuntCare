package com.example.stuntcare

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.raon.R

class AntiStunting2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anti_stunting2)
        val historyOfIllness = intent.getStringExtra("historyOfIllness")

        // Menampilkan data pada TextView
//        val textViewHistoryOfIllness = findViewById<TextView>(R.id.editTextText10)
//        textViewHistoryOfIllness.text = historyOfIllness
    }
}